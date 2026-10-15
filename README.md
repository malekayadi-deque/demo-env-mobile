# Embel Shop - Android

An e-commerce demo Android application built with Kotlin and Jetpack Compose, integrated with axe DevTools for automated accessibility testing.

## Language & Frameworks

| Category | Technology | Version |
|---|---|---|
| **Language** | Kotlin | 2.3.0 |
| **UI Framework** | Jetpack Compose (Material 3) | BOM 2026.01.01 |
| **Dependency Injection** | Hilt | 2.57.1 |
| **Navigation** | Compose Navigation | 2.9.7 |
| **Data Persistence** | DataStore Preferences | 1.2.0 |
| **Image Loading** | Coil | 3.3.0 |
| **Maps** | Google Maps Compose | 8.0.1 |
| **Serialization** | Kotlinx Serialization (JSON) | 1.10.0 |
| **Accessibility Testing** | axe DevTools Android (Deque) | 8.2.0 |
| **Build System** | Gradle (Kotlin DSL) | 8.13 |
| **Java Compatibility** | Java | 17 |
| **Android SDK** | minSdk 26 / targetSdk 36 / compileSdk 36 | |

### Architecture

The project follows the **MVVM (Model-View-ViewModel)** pattern with a **Repository** layer:

```
app/src/main/java/com/embel/shop/
├── data/           # Models, repositories, local data, DataStore
├── di/             # Hilt dependency injection modules
├── navigation/     # Compose Navigation graph and routes
├── ui/
│   ├── components/ # Reusable Compose UI components
│   ├── screens/    # Full-screen composables (Home, Shop, Cart, etc.)
│   └── theme/      # Material 3 theme (colors, typography, shapes)
└── viewmodel/      # ViewModels for each screen
```

## Prerequisites

- **Android SDK** with platform tools and build tools installed
- **Java 17** (JDK)
- An **Android Virtual Device (AVD)** created via Android Studio's AVD Manager or the command line
- The `ANDROID_HOME` (or `ANDROID_SDK_ROOT`) environment variable set to your SDK path
- `emulator` and `adb` available on your `PATH` (typically at `$ANDROID_HOME/emulator` and `$ANDROID_HOME/platform-tools`)

## New Device Setup

### 1. Install prerequisites

- **Java 17 (JDK)** — required for the build
- **Android Studio** (recommended) or standalone Android SDK
  - Must include: Android SDK platform tools, build tools, and SDK platform for API 36

### 2. Set environment variables

**macOS / Linux**
```bash
export ANDROID_HOME=/path/to/your/android/sdk
export PATH=$PATH:$ANDROID_HOME/emulator:$ANDROID_HOME/platform-tools
```
The default Android Studio SDK path on macOS is `~/Library/Android/sdk`.

**Windows (Command Prompt)**
```cmd
set ANDROID_HOME=C:\Users\<YourName>\AppData\Local\Android\Sdk
set PATH=%PATH%;%ANDROID_HOME%\emulator;%ANDROID_HOME%\platform-tools
```

**Windows (PowerShell)**
```powershell
$env:ANDROID_HOME = "C:\Users\<YourName>\AppData\Local\Android\Sdk"
$env:PATH += ";$env:ANDROID_HOME\emulator;$env:ANDROID_HOME\platform-tools"
```

> To make these permanent on Windows, set them via **System Properties → Environment Variables**.

### 3. Clone the repo

Clone the repository and open it in Android Studio (or your editor of choice).

### 4. Create an emulator

If you don't have one already, create an AVD via **Android Studio → Device Manager → Create Virtual Device**. A Pixel device with API 35+ is recommended.

### 5. Configure axe DevTools credentials

The axe DevTools library is hosted on a private Deque Maven repository. You need valid credentials to download it, plus an API key and project ID to upload scan results to the cloud dashboard.

#### 5a. Add Maven repository credentials to `local.properties`

A template is provided — copy it and fill in your values:

```bash
# macOS / Linux
cp local.properties.example local.properties
```

```
:: Windows (Command Prompt)
copy local.properties.example local.properties
```

> **Windows note:** in `local.properties`, `sdk.dir` must have both the drive colon and the backslashes escaped, e.g.
> `sdk.dir=C\:\\Users\\<you>\\AppData\\Local\\Android\\Sdk`
> An unescaped Windows path is the most common cause of a failed first sync.

Then add:

```properties
axe.username=your.email@example.com
axe.password=your_artifactory_token
```

`local.properties` is git-ignored, so these values stay on your machine only. The Maven repository URL is already configured in `settings.gradle.kts` and will pick up the credentials automatically.

To obtain your Artifactory token:
1. Log in to [axe.deque.com](https://axe.deque.com)
2. Go to **Account → API Access** (or contact your Deque account manager)
3. Copy your username (email) and generate/copy an Artifactory access token

#### 5b. Add your API key and project ID to `local.properties`

In the same `local.properties` file, also add:

```properties
axe.apiKey=your-api-key-here
axe.projectId=your-project-id-here
```

These are read by `app/build.gradle.kts` at build time and injected as `BuildConfig` fields. You can find both values on the [axe DevTools cloud dashboard](https://axe.deque.com) under your project settings.

## Running the App on an Emulator

### 1. List available emulators

```bash
emulator -list-avds
```

This prints the names of all AVDs you have created (e.g., `Pixel_9_Pro_XL_API_35`).

### 2. Start the emulator

**macOS / Linux**
```bash
emulator -avd <AVD_NAME> &
```
The `&` runs it in the background so your terminal stays free.

**Windows (Command Prompt)**
```cmd
start emulator -avd <AVD_NAME>
```

**Windows (PowerShell)**
```powershell
Start-Process emulator -ArgumentList "-avd <AVD_NAME>"
```

Replace `<AVD_NAME>` with one of the names from step 1.

Wait until the emulator has fully booted. You can check with:

```bash
adb wait-for-device
```

### 3. Build and install the app

From the project root directory:

**macOS / Linux**
```bash
./gradlew installDebug
```

**Windows**
```cmd
gradlew.bat installDebug
```

### 4. Launch the app

```bash
adb shell am start -n com.embel.shop/.MainActivity
```

This command is the same on all platforms.

## Running Accessibility Tests

The accessibility tests are **instrumented tests** that run on a device or emulator. Make sure an emulator is running (see steps above) before executing them.

### Run all instrumented tests

**macOS / Linux**
```bash
./gradlew connectedDebugAndroidTest
```

**Windows**
```cmd
gradlew.bat connectedDebugAndroidTest
```

### Run only the accessibility test class

**macOS / Linux**
```bash
./gradlew connectedDebugAndroidTest \
  -Pandroid.testInstrumentationRunnerArguments.class=com.embel.shop.AxeAccessibilityTest
```

**Windows (Command Prompt)**
```cmd
gradlew.bat connectedDebugAndroidTest -Pandroid.testInstrumentationRunnerArguments.class=com.embel.shop.AxeAccessibilityTest
```

**Windows (PowerShell)**
```powershell
gradlew.bat connectedDebugAndroidTest `
  -Pandroid.testInstrumentationRunnerArguments.class=com.embel.shop.AxeAccessibilityTest
```

### Run a single accessibility test method

**macOS / Linux**
```bash
./gradlew connectedDebugAndroidTest \
  -Pandroid.testInstrumentationRunnerArguments.class=com.embel.shop.AxeAccessibilityTest#homeScreen_accessibilityScan
```

**Windows (Command Prompt)**
```cmd
gradlew.bat connectedDebugAndroidTest -Pandroid.testInstrumentationRunnerArguments.class=com.embel.shop.AxeAccessibilityTest#homeScreen_accessibilityScan
```

**Windows (PowerShell)**
```powershell
gradlew.bat connectedDebugAndroidTest `
  -Pandroid.testInstrumentationRunnerArguments.class=com.embel.shop.AxeAccessibilityTest#homeScreen_accessibilityScan
```

### Test results

HTML test reports are generated at:

**macOS / Linux**
```
app/build/reports/androidTests/connected/debug/index.html
```

**Windows**
```
app\build\reports\androidTests\connected\debug\index.html
```

## Accessibility Tests

## Prerequisites 
Get axeDevtools Library using the artifactory Maven Central(https://docs.deque.com/devtools-mobile/2025.7.2/en/android-library)

### Location

```
app/src/androidTest/java/com/embel/shop/AxeAccessibilityTest.kt
```

This is the only test file in the project. There are no unit tests in `app/src/test/`.

### How They Work

The tests use **axe DevTools Android** (by Deque) to perform automated accessibility audits on each screen of the app. Here is the flow:

1. **Setup** - The test class creates an `AxeDevTools` instance and authenticates with the axe DevTools cloud using an API key and project ID (defined as `BuildConfig` fields in the debug build type).

2. **Launch** - A Compose test rule (`createAndroidComposeRule<MainActivity>()`) launches the app's `MainActivity` before each test.

3. **Navigate** - Each test method uses the Compose testing APIs (`onNodeWithText`, `onNodeWithContentDescription`, `performClick`) to navigate to a specific screen.

4. **Scan** - The `axe.scan()` method analyzes the current screen for accessibility issues (WCAG violations such as missing content descriptions, insufficient color contrast, touch target sizes, etc.).

5. **Upload & Save** - Results are uploaded to the axe DevTools cloud dashboard and also saved to local device storage for offline review.

6. **Teardown** - After each test, `axe.tearDown()` cleans up the session.

### Test Coverage

| Test Method | Screen | Navigation |
|---|---|---|
| `homeScreen_accessibilityScan` | Home | App launch (default screen) |
| `shopScreen_accessibilityScan` | Shop | Clicks "Shop" in navigation |
| `cartScreen_accessibilityScan` | Cart | Clicks "Cart" in navigation |
| `loginScreen_accessibilityScan` | Login | Clicks "Account" icon (content description) |
| `productDetailScreen_accessibilityScan` | Product Detail | Shop > clicks "End Grain Cutting Board" |

---

## Appium Autoscan

A second, standalone accessibility test uses **Appium + axe DevTools Mobile** to drive the app externally over ADB and automatically capture every screen. Unlike the instrumented tests above, this runs entirely outside the Android build — no Gradle involvement after the APK is built.

**Script location:** `tests/accessibility/autoscan.js`  
**Results location:** `axe-results/` in the project root (git-ignored)

### Prerequisites

| Tool | Required version | Check |
|---|---|---|
| Node.js | 20 or above | `node --version` |
| Appium | 3.x (global) | `appium --version` |
| axe UIAutomator2 driver | 1.3.0 (Appium 3) | `appium driver list --installed` |
| `~/.npmrc` | Deque registry configured | see step 1 below |

#### 1. Configure npm credentials for the Deque registry

Add the following to `~/.npmrc` (create the file if it does not exist):

```
@axe-devtools:registry=https://agora.dequecloud.com/artifactory/api/npm/axe-devtools-mobile-appium/
//agora.dequecloud.com/artifactory/api/npm/axe-devtools-mobile-appium/:_auth="<base64-encoded user:token>"
//agora.dequecloud.com/artifactory/api/npm/axe-devtools-mobile-appium/:email=your.email@example.com
//agora.dequecloud.com/artifactory/api/npm/axe-devtools-mobile-appium/:always-auth=true
```

Obtain your Artifactory token from [axe.deque.com](https://axe.deque.com) → **Account → API Access**.  
The `_auth` value is `base64(email:token)` — generate it with:

```bash
echo -n "your.email@example.com:your_token" | base64
```

#### 2. Install Appium globally

```bash
npm install -g appium
```

#### 3. Install the axe UIAutomator2 driver (Appium 3)

```bash
appium driver install --source=npm @axe-devtools/axe-appium3-uiautomator2-driver
```

Verify it is listed:

```bash
appium driver list --installed
# axeuiautomator2@1.3.0 [installed (npm)]
```

> **Note:** If you see `axeuiautomator2@2.x` instead, that is the Appium 2 driver and will not support the autoscan API. Uninstall it and reinstall as above:
> ```bash
> appium driver uninstall axeuiautomator2
> appium driver install --source=npm @axe-devtools/axe-appium3-uiautomator2-driver
> ```

#### 4. Install Node dependencies

```bash
cd tests/accessibility
npm install
```

### Running the Autoscan

The script reads `axe.apiKey` and `axe.projectId` directly from `local.properties` — no extra credential setup is needed beyond what you configured in [step 5 of New Device Setup](#5-configure-axe-devtools-credentials).

#### Step 1 — Build the debug APK

**macOS / Linux**
```bash
./gradlew assembleDebug
```

**Windows**
```cmd
gradlew.bat assembleDebug
```

#### Step 2 — Start your emulator

```bash
emulator -list-avds                  # find your AVD name
emulator -avd <AVD_NAME> &           # macOS/Linux (background)
adb wait-for-device                  # wait until fully booted
```

**Windows (Command Prompt)**
```cmd
start emulator -avd <AVD_NAME>
adb wait-for-device
```

#### Step 3 — Start the Appium server

Open a **separate terminal** and leave it running for the duration of the test:

```bash
appium
```

#### Step 4 — Run the autoscan

**macOS / Linux**
```bash
node tests/accessibility/autoscan.js
```

**Windows**
```cmd
node tests\accessibility\autoscan.js
```

The script installs the APK, launches the app, navigates through five screens (Home → Shop → Product Detail → Cart → Login), and stops the session.

### Results

Results are saved to:

```
axe-results/
```

in the project root. This folder is git-ignored. If you want to redirect results elsewhere, set the `AXE_RESULTS_DIR` environment variable before running:

**macOS / Linux**
```bash
AXE_RESULTS_DIR=/path/to/output node tests/accessibility/autoscan.js
```

**Windows (Command Prompt)**
```cmd
set AXE_RESULTS_DIR=C:\path\to\output
node tests\accessibility\autoscan.js
```

Results are also uploaded to the [axe DevTools cloud dashboard](https://axe.deque.com) under the project configured by `axe.projectId` in `local.properties`.

### Screens Covered

| Screen | How the script navigates there |
|---|---|
| Home | App launch default |
| Shop | Taps "Shop" in the bottom navigation bar |
| Product Detail | Taps "End Grain Cutting Board" from the Shop screen |
| Cart | Taps "Cart" in the bottom navigation bar |
| Login | Taps the "Account" icon (content description) in the bottom navigation bar |
