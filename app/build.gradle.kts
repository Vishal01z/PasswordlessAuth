plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.passwordlessauth"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.passwordlessauth"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    buildFeatures {
        compose = true
        viewBinding = false   // ⚠️ IMPORTANT (XML rahega but inactive)
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.13"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    // Compose
    implementation(libs.compose.ui)
    implementation(libs.compose.material3)
    implementation(libs.compose.ui.tooling.preview)
    debugImplementation(libs.compose.ui.tooling)

    // IMPORTANT: XML Material3 theme support
    implementation("com.google.android.material:material:1.12.0")
    implementation("com.jakewharton.timber:timber:5.0.1")
    implementation("androidx.navigation:navigation-compose:2.7.7")

    // Activity + ViewModel
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    // Coroutines
    implementation(libs.kotlinx.coroutines.android)
}

