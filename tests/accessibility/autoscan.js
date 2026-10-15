'use strict';

const { remote } = require('webdriverio');
const path = require('path');
const fs = require('fs');

// ---------------------------------------------------------------------------
// Configuration
// ---------------------------------------------------------------------------

// Reads key=value pairs from local.properties, ignoring comments and blanks.
function readLocalProperties() {
  const propsFile = path.resolve(__dirname, '../../local.properties');
  if (!fs.existsSync(propsFile)) return {};
  return Object.fromEntries(
    fs.readFileSync(propsFile, 'utf-8')
      .split('\n')
      .filter(line => line.includes('=') && !line.trimStart().startsWith('#'))
      .map(line => {
        const idx = line.indexOf('=');
        return [line.slice(0, idx).trim(), line.slice(idx + 1).trim()];
      })
  );
}

const localProps = readLocalProperties();

// Credentials: env vars take priority, then local.properties
const AXE_API_KEY    = process.env.AXE_API_KEY    || localProps['axe.apiKey']    || '';
const AXE_PROJECT_ID = process.env.AXE_PROJECT_ID || localProps['axe.projectId'] || '';

// APK to install — built by: ./gradlew assembleDebug
const APK_PATH = process.env.APK_PATH ||
  path.resolve(__dirname, '../../app/build/outputs/apk/debug/app-debug.apk');

// Appium server URL (default: localhost:4723)
const APPIUM_HOST = process.env.APPIUM_HOST || 'localhost';
const APPIUM_PORT = parseInt(process.env.APPIUM_PORT || '4723', 10);

// Where to keep results — defaults to the project root
const RESULTS_DEST = process.env.AXE_RESULTS_DIR ||
  path.resolve(__dirname, '../../axe-results');

// ---------------------------------------------------------------------------
// Pre-flight checks
// ---------------------------------------------------------------------------

function preflight() {
  let ok = true;

  if (!AXE_API_KEY) {
    console.error('ERROR: axe API key not found.');
    console.error('  → Add axe.apiKey=<key> to local.properties, or set AXE_API_KEY env var.');
    ok = false;
  }

  if (!fs.existsSync(APK_PATH)) {
    console.error(`ERROR: APK not found at:\n  ${APK_PATH}`);
    console.error('  → Build it first: ./gradlew assembleDebug');
    ok = false;
  }

  if (!ok) process.exit(1);
}

// ---------------------------------------------------------------------------
// Navigation helpers
// ---------------------------------------------------------------------------

// UIAutomator2 selector by visible text (matches Compose semantics text node)
const byText = (text) => `android=new UiSelector().text("${text}")`;

// UIAutomator2 selector by content description (contentDescription in Compose)
const byDesc = (desc) => `android=new UiSelector().description("${desc}")`;

async function tap(driver, selector, label) {
  const el = await driver.$(selector);
  await el.waitForDisplayed({ timeout: 8000 });
  await el.click();
  // Brief pause so the screen transition settles before the driver captures it.
  await driver.pause(2000);
  console.log(`  ✓ Navigated to ${label}`);
}

// ---------------------------------------------------------------------------
// Main
// ---------------------------------------------------------------------------

async function main() {
  preflight();

  console.log('');
  console.log('=== Embel Android — axe DevTools Autoscan ===');
  console.log(`Appium:     ${APPIUM_HOST}:${APPIUM_PORT}`);
  console.log(`APK:        ${APK_PATH}`);
  console.log(`Project ID: ${AXE_PROJECT_ID || '(none — results not tagged to a project)'}`);
  console.log('');

  // -------------------------------------------------------------------------
  // 1. Connect to Appium with the AxeUiAutomator2 driver
  //
  // automationName 'AxeUiAutomator2' is the exact string that switches Appium
  // from the standard UiAutomator2 driver to Deque's patched driver, which
  // intercepts every screen-change event and runs axe rules automatically.
  // -------------------------------------------------------------------------
  const driver = await remote({
    hostname: APPIUM_HOST,
    port: APPIUM_PORT,
    path: '/',
    logLevel: 'warn',
    capabilities: {
      platformName: 'Android',
      'appium:automationName': 'AxeUiAutomator2',
      'appium:app': APK_PATH,
      'appium:appPackage': 'com.embel.shop',
      'appium:appActivity': 'com.embel.shop.MainActivity',
      'appium:noReset': false,           // fresh install ensures a clean state
      'appium:newCommandTimeout': 120,   // seconds before Appium drops idle session
    },
  });

  try {
    // -----------------------------------------------------------------------
    // 2. Start axe autoscan session
    //
    // From this point onward every screen the driver sees is captured and
    // scanned for WCAG violations. No per-screen scan call is needed.
    // -----------------------------------------------------------------------
    const scanConfig = { axeMobileApiKey: AXE_API_KEY };
    if (AXE_PROJECT_ID) scanConfig.axeProjectId = AXE_PROJECT_ID;

    await driver.executeScript('mobile: axeStartAutoScanSession', [scanConfig]);
    console.log('Autoscan session started.\n');

    // -----------------------------------------------------------------------
    // 3. Navigate through each screen
    //
    // The Compose app exposes its bottom nav items as text/description
    // semantics — the same selectors used in AxeAccessibilityTest.kt.
    // UIAutomator2 reads these directly from the accessibility tree.
    // -----------------------------------------------------------------------
    console.log('Navigating screens (each pause allows the scan to capture the frame):');

    // Home — already the start destination in EmbelNavGraph, just wait for idle
    await driver.pause(2000);
    console.log('  ✓ Scanned: Home screen');

    // Shop
    await tap(driver, byText('Shop'), 'Shop screen');

    // Product Detail — "End Grain Cutting Board" is the first product in
    // ProductData.shopProducts, matching the existing instrumented test
    await tap(driver, byText('End Grain Cutting Board'), 'Product Detail screen');

    // Cart — navigate via bottom bar (go back to top-level first via Shop,
    // then Cart, so the bottom bar is in view)
    await tap(driver, byText('Cart'), 'Cart screen');

    // Login / Account — content description "Account" on the account icon
    await tap(driver, byDesc('Account'), 'Login screen');

    // -----------------------------------------------------------------------
    // 4. Stop autoscan and collect results
    //
    // The driver finalises all pending scans, uploads to axe Developer Hub
    // (if axeProjectId was supplied), saves a local HTML report, and returns
    // a summary object.
    // -----------------------------------------------------------------------
    console.log('\nStopping autoscan session...');
    const results = await driver.executeScript('mobile: axeStopAutoScanSession', []);

    // Move results from the driver's default output location to RESULTS_DEST.
    // The driver always writes to its own build/ folder; there is no API param
    // to override that path, so we relocate after the session closes.
    let finalResultsPath = results.localDirectory;
    if (results.localDirectory && fs.existsSync(results.localDirectory)) {
      fs.mkdirSync(RESULTS_DEST, { recursive: true });
      fs.cpSync(results.localDirectory, RESULTS_DEST, { recursive: true, force: true });
      fs.rmSync(results.localDirectory, { recursive: true, force: true });
      finalResultsPath = RESULTS_DEST;
    }

    console.log('');
    console.log('==========================================');
    console.log(' AUTOSCAN COMPLETE');
    console.log('==========================================');
    console.log(` Screens processed : ${results.processedCount}/${results.fileCount}`);
    console.log(` Results saved to  : ${finalResultsPath}`);
    if (results.reportUrl) {
      console.log(` Dashboard URL     : ${results.reportUrl}`);
    }
    console.log('==========================================');

  } finally {
    // Always end the Appium session, even on error
    await driver.deleteSession();
  }
}

main().catch((err) => {
  console.error('\nAutoscan failed:', err.message || err);
  process.exit(1);
});
