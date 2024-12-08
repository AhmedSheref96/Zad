plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.googleServices)
    alias(libs.plugins.firebaseCrashlytics)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.el3sas.zad"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.el3sas.zad"
        minSdk = 21
        targetSdk = 34
        versionCode = 101
        versionName = "v1.0.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    signingConfigs {
        create("release") {
            val keystorePath = System.getenv("KEYSTORE_PATH") ?: "local/path/to/keystore.jks"
            val keystorePassword = System.getenv("KEYSTORE_PASSWORD") ?: "local_keystore_password"
            val keystoreAlias = System.getenv("KEY_ALIAS") ?: "local_key_alias"
            val keystoreKEYPassword = System.getenv("KEY_PASSWORD") ?: "local_key_password"

            storeFile = file(keystorePath)
            storePassword = keystorePassword
            keyAlias = keystoreAlias
            keyPassword = keystoreKEYPassword
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(platform(libs.firebaseBom))
    implementation(libs.firebaseCrashlyticsKtx)
    implementation(libs.firebaseAnalyticsKtx)

    implementation(libs.ksp.gradlePlugin)
    implementation(libs.ksp.api)
    implementation(libs.ksp)

    implementation(libs.androidx.work.runtime)

    implementation(libs.hilt.android)
    implementation(libs.androidx.hilt.navigation.compose)
    ksp(libs.hilt.android.compiler)

    implementation(libs.androidx.hilt.worker)
    ksp(libs.androidx.hilt.worker.compiler)

    implementation(project(":system-design"))
    implementation(project(":feat-courses"))
    implementation(project(":domain"))
    implementation(project(":data"))
}
