import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
    id("org.jetbrains.kotlin.plugin.serialization")
}

val localProps = Properties().apply {
    val f = rootProject.file("local.properties")
    if (f.exists()) load(f.inputStream())
}

android {
    namespace = "com.embel.shop"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.embel.shop"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            buildConfigField("String", "AXE_DEVTOOLS_APIKEY", "\"${localProps["axe.apiKey"] ?: ""}\"")
            buildConfigField("String", "AXE_DEVTOOLS_PROJECT_ID", "\"${localProps["axe.projectId"] ?: ""}\"")
            buildConfigField("String", "AXE_DEVTOOLS_SERVER_URL", "\"https://axe.deque.com\"")
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    testOptions {
        animationsDisabled = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "/META-INF/DEPENDENCIES"
        }
    }
}

kotlin {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
    }
}

dependencies {
    // Compose BOM
    val composeBom = platform("androidx.compose:compose-bom:2026.01.01")
    implementation(composeBom)
    androidTestImplementation(composeBom)

    // Compose UI
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.foundation:foundation")
    implementation("androidx.compose.material:material-icons-extended")
    implementation("androidx.compose.animation:animation")

    // Activity Compose
    implementation("androidx.activity:activity-compose:1.12.4")

    // Navigation Compose
    implementation("androidx.navigation:navigation-compose:2.9.7")

    // ViewModel + Lifecycle
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.10.0")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.10.0")

    // DataStore
    implementation("androidx.datastore:datastore-preferences:1.2.0")

    // Image loading - Coil 3
    implementation("io.coil-kt.coil3:coil-compose:3.3.0")
    implementation("io.coil-kt.coil3:coil-network-okhttp:3.3.0")

    // Google Maps
    implementation("com.google.maps.android:maps-compose:8.0.1")
    implementation("com.google.android.gms:play-services-maps:19.2.0")

    // Hilt DI
    implementation("com.google.dagger:hilt-android:2.57.1")
    ksp("com.google.dagger:hilt-compiler:2.57.1")
    implementation("androidx.hilt:hilt-navigation-compose:1.3.0")

    // Force kotlin-metadata-jvm to match Kotlin 2.3.0 (unshaded since Hilt 2.57)
    ksp("org.jetbrains.kotlin:kotlin-metadata-jvm:2.3.0")

    // Kotlinx Serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.10.0")

    // Core
    implementation("androidx.core:core-ktx:1.17.0")

    // Material (required by axeDevTools transitive dependencies)
    implementation("com.google.android.material:material:1.12.0")

    // Testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.3.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.7.0")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // axeDevTools - Accessibility Testing
    androidTestImplementation("com.deque.android:axe-devtools-android:8.2.0")

    // MLKit for axeDevTools text recognition
    debugImplementation("com.google.mlkit:text-recognition:16.0.1")

    // UiAutomator (used by axeDevTools for device interaction)
    androidTestImplementation("androidx.test.uiautomator:uiautomator:2.4.0-beta01")

    // Test rules & runner
    androidTestImplementation("androidx.test:rules:1.7.0")
    androidTestImplementation("androidx.test:runner:1.7.0")
}
