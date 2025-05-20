plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
}

android {
    namespace = "com.example.edulearn"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.edulearn"
        minSdk = 24
        targetSdk = 34
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

/*    compileOptions {
        sourceCompatibility = VERSION_11
        targetCompatibility = VERSION_11
    }*/

    kotlinOptions {
        jvmTarget = "1"
        freeCompilerArgs = freeCompilerArgs + listOf("-Xjvm-default=all")
    }

    buildFeatures {
        viewBinding = true
    }

    kotlin {
        jvmToolchain(1)
    }
}

dependencies {
    // Core Android
    implementation("androidx.core:core-ktx:1.12.0") // Use -ktx for Kotlin
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // Navigation Component
    val navVersion = "2.7.5"
    implementation("androidx.navigation:navigation-fragment-ktx:$navVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navVersion")

    // Lifecycle components
    val lifecycleVersion = "2.6.2"
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion")

    // RecyclerView
    implementation("androidx.recyclerview:recyclerview:1.3.2")

    // Retrofit
    val retrofitVersion = "2.9.0"
    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")

    // Coroutines (recommended with Retrofit)
    val coroutinesVersion = "1.7.3"
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")

    // Glide
    val glideVersion = "4.16.0"
    implementation("com.github.bumptech.glide:glide:$glideVersion")
    kapt("com.github.bumptech.glide:compiler:$glideVersion") // Changed to kapt

    // ViewPager2
    implementation("androidx.viewpager2:viewpager2:1.0.0")

    // Swipe Refresh
    implementation("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")

    // Image Picker
    implementation("com.github.dhaval2404:imagepicker:2.1")

    // PDF Viewer
    implementation("com.github.barteksc:AndroidPdfViewer:3.1.0-beta.1")

    // Room
    val roomVersion = "2.6.0"
    implementation("androidx.room:room-runtime:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion") // For Kotlin extensions
    kapt("androidx.room:room-compiler:$roomVersion") // Changed to kapt

    // Testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}