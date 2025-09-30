plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.examenparcial1.universidadapp"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.examenparcial1.universidadapp"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures {
        viewBinding = true
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    // Room
    implementation (libs.room.runtime)
    annotationProcessor (libs.room.compiler)

    // Lifecycle
    implementation (libs.lifecycle.livedata)
    implementation (libs.lifecycle.viewmodel)

    // Navigation
    implementation (libs.navigation.fragment)
    implementation (libs.navigation.ui)

    // RecyclerView
    implementation (libs.recyclerview)
}