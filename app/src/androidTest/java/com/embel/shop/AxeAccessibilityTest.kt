package com.embel.shop

import android.util.Log
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import com.deque.mobile.devtools.AxeDevTools
import org.junit.AfterClass
import org.junit.Assert.assertNotNull
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test

class AxeAccessibilityTest {

    companion object {
        private const val TAG = "AxeA11yTest"
        private val axe = AxeDevTools()

        @BeforeClass
        @JvmStatic
        fun setupSession() {
            axe.setInstrumentation(InstrumentationRegistry.getInstrumentation())
            axe.startSession(
                apiKey = BuildConfig.AXE_DEVTOOLS_APIKEY,
                projectId = BuildConfig.AXE_DEVTOOLS_PROJECT_ID
            )
            Log.d(TAG, "Session started. Authenticated: ${axe.isUserAuthenticated()}")
        }

        @AfterClass
        @JvmStatic
        fun tearDownSession() {
            axe.tearDown()
            Log.d(TAG, "Session closed.")
        }
    }

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private fun scanAndUpload(screenName: String, tags: Set<String> = setOf("Embel")) {
        axe.setScanName(screenName)
        axe.tagScanAs(tags)

        val result = axe.scan()
        assertNotNull("Scan returned null for $screenName", result)

        Log.d(TAG, "Scan OK for $screenName, uploading...")

        try {
            val uploadResult = result!!.uploadToDashboard()
            Log.d(TAG, "Upload SUCCESS for $screenName: $uploadResult")
        } catch (e: Exception) {
            Log.e(TAG, "Upload FAILED for $screenName", e)
            throw e
        }

        result.saveResultToLocalStorage(screenName)
        Log.d(TAG, "Saved locally: $screenName")
    }

    @Test
    fun homeScreen_accessibilityScan() {
        composeTestRule.waitForIdle()
        scanAndUpload("Home Screen", setOf("Embel", "Home"))
    }

    @Test
    fun shopScreen_accessibilityScan() {
        composeTestRule.onNodeWithText("Shop").performClick()
        composeTestRule.waitForIdle()
        scanAndUpload("Shop Screen", setOf("Embel", "Shop"))
    }

    @Test
    fun cartScreen_accessibilityScan() {
        composeTestRule.onNodeWithText("Cart").performClick()
        composeTestRule.waitForIdle()
        scanAndUpload("Cart Screen", setOf("Embel", "Cart"))
    }

    @Test
    fun loginScreen_accessibilityScan() {
        composeTestRule.onNodeWithContentDescription("Account").performClick()
        composeTestRule.waitForIdle()
        scanAndUpload("Login Screen", setOf("Embel", "Login"))
    }

    @Test
    fun productDetailScreen_accessibilityScan() {
        composeTestRule.onNodeWithText("Shop").performClick()
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithText("End Grain Cutting Board").performClick()
        composeTestRule.waitForIdle()
        scanAndUpload("Product Detail Screen", setOf("Embel", "ProductDetail"))
    }
}
