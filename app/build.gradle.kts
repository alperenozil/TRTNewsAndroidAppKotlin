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
    compileSdk = 35
    defaultConfig {
        applicationId = "com.news.assignment.rss"
        minSdk = 30
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "com.google.dagger.hilt.android.testing.HiltTestRunner"
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

    packaging {
        resources {
            exclude("META-INF/LICENSE.md")
            exclude("META-INF/LICENSE-notice.md")
        }
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
    implementation(libs.androidx.ui.test.junit4.android)
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
    androidTestImplementation(libs.androidx.test.runner)
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    implementation(libs.androidx.lifecycle.viewmodel.compose.v261)
    implementation(libs.androidx.hilt.navigation.compose)
    // Room
    implementation(libs.androidx.room.runtime.v250)
    kapt (libs.androidx.room.compiler.v250)

    // Kotlin Extensions for Room
    implementation(libs.androidx.room.ktx.v250)

    // Hilt Testing dependencies (optional for testing)
    androidTestImplementation(libs.hilt.android.testing)
    kaptAndroidTest(libs.androidx.hilt.compiler)

    // Coroutine dependencies for Room
    implementation(libs.kotlinx.coroutines.android)

    //Coil
    implementation(libs.coil.compose.v240)




    testImplementation(libs.junit)
    testImplementation(libs.kotlin.test)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.mockk)
    androidTestImplementation(libs.mockk.android)

    // Mockito
    testImplementation (libs.mockito.core)
    testImplementation (libs.mockito.kotlin)
    testImplementation (libs.mockito.inline)

    // Coroutines Test
    testImplementation (libs.kotlinx.coroutines.test.v173)
    testImplementation (libs.turbine)

    testImplementation(libs.androidx.core.testing)

    androidTestImplementation(libs.androidx.navigation.testing)
    kaptAndroidTest(libs.hilt.android.compiler)
    debugImplementation(libs.androidx.ui.test.manifest)

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.2")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.8.2")

}

kapt {
    correctErrorTypes = true
}