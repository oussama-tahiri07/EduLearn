plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.edulearn"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.edulearn"
        minSdk = 28
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
// Supabase core
    implementation("io.github.jan-tennert.supabase:supabase-kt:1.4.0")

// Supabase modules
    implementation("io.github.jan-tennert.supabase:gotrue-kt:1.4.0")
    implementation("io.github.jan-tennert.supabase:postgrest-kt:1.4.0")

// Coroutines (needed by supabase-kt)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

// JSON
    implementation("com.google.code.gson:gson:2.10.1")
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)

    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}