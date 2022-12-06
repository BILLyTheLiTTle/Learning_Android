plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "learning.android.kmm.android"
    compileSdk = 32
    defaultConfig {
        applicationId = "learning.android.kmm.android"
        minSdk = 21
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.0"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    flavorDimensions.add("aFlavorDimension")

    productFlavors {
        create("FlavorOne") {
            dimension = "aFlavorDimension"
            buildConfigField("String", "FLAVOR_NAME", "\"Flavor 1\"")
        }

        create("FlavorTwo") {
            dimension = "aFlavorDimension"
            buildConfigField("String", "FLAVOR_NAME", "\"Flavor 2\"")
        }
    }
}

dependencies {
    implementation(project(":shared"))
    with(Dependencies.Compose) {
        implementation(ui)
        implementation(uiTooling)
        implementation(uiToolingPreview)
        implementation(foundation)
        implementation(material)
        implementation(activity)
    }
//    implementation("androidx.compose.ui:ui:1.2.1")
//    implementation("androidx.compose.ui:ui-tooling:1.2.1")
//    implementation("androidx.compose.ui:ui-tooling-preview:1.2.1")
//    implementation("androidx.compose.foundation:foundation:1.2.1")
//    implementation("androidx.compose.material:material:1.2.1")
//    implementation("androidx.activity:activity-compose:1.5.1")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    implementation("io.coil-kt:coil-compose:2.1.0")

    val coreVersion = "3.2.2"
    // Koin Core features
    implementation("io.insert-koin:koin-core:$coreVersion")
    // Koin Test features
    testImplementation("io.insert-koin:koin-test:$coreVersion")
    // Koin for JUnit 4
    testImplementation("io.insert-koin:koin-test-junit4:$coreVersion")
    // Koin for JUnit 5
    testImplementation("io.insert-koin:koin-test-junit5:$coreVersion")

    val koinAndroidVersion= "3.3.0"
    // Koin main features for Android
    implementation("io.insert-koin:koin-android:$koinAndroidVersion")
    // Java Compatibility
    implementation("io.insert-koin:koin-android-compat:$koinAndroidVersion")
    // Jetpack WorkManager
    implementation("io.insert-koin:koin-androidx-workmanager:$koinAndroidVersion")
    // Navigation Graph
    implementation("io.insert-koin:koin-androidx-navigation:$koinAndroidVersion")

//    val koinAndroidComposeVersion= "3.3.0"
//    // Jetpack Compose
//    implementation("io.insert-koin:koin-androidx-compose:$koinAndroidComposeVersion")
}