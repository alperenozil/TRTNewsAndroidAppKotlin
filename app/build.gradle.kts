import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose.compiler)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.news.assignment.rss"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.news.assignment.rss"
        minSdk = 30
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_17)
        }
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.kotlin.stdlib)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.core.ktx)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.activity.compose)
    implementation(libs.retrofit)
    implementation(libs.gson)
    testImplementation(libs.junit.junit)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.viewModelCompose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.compose.animation)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.material.iconsExtended)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.runtime.livedata)
    implementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.compose.ui.tooling.preview)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.androidx.test.runner)
    implementation("com.google.dagger:hilt-android:2.51.1")
    kapt("com.google.dagger:hilt-android-compiler:2.51.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
    // Room
    implementation("androidx.room:room-runtime:2.5.0")
    kapt ("androidx.room:room-compiler:2.5.0")

    // Kotlin Extensions for Room
    implementation("androidx.room:room-ktx:2.5.0")

    // Hilt Testing dependencies (optional for testing)
    androidTestImplementation("androidx.hilt:hilt-lifecycle-viewmodel:1.0.0")
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.51.1")
    kaptAndroidTest("androidx.hilt:hilt-compiler:1.0.0")

    // Coroutine dependencies for Room
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    //Coil
    implementation("io.coil-kt:coil-compose:2.4.0")


    testImplementation("junit:junit:4.13.2")
    testImplementation("org.jetbrains.kotlin:kotlin-test:1.8.0") // for Kotlin's assertEquals and other test functions
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.3") // Replace with the latest version
    testImplementation("io.mockk:mockk:1.13.2")
    androidTestImplementation("io.mockk:mockk-android:1.13.2")

    testImplementation("androidx.arch.core:core-testing:2.2.0")
    androidTestImplementation("app.cash.paparazzi:paparazzi:1.3.1")


}

kapt {
    correctErrorTypes = true
}