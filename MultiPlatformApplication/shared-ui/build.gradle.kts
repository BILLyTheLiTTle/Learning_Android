import org.jetbrains.compose.compose

plugins {
    kotlin(multiplatform)
    id(androidLib)
    id(composePlugin) version Versions.desktop_compose_plugin
}

//android {
//    compileSdk =  Versions.compile_sdk
//    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
//    defaultConfig {
//        minSdk = Versions.min_sdk
//        targetSdk = Versions.target_sdk
//    }
//    buildTypes {
//        getByName("release") {
//            isMinifyEnabled = false
//        }
//    }
//}



android {
    namespace = "learning.android.kmm.android.ui"
    compileSdk = 32
    defaultConfig {
        minSdk = 21
        targetSdk = 32
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.0"
    }
//    packagingOptions {
//        resources {
//            excludes += "/META-INF/{AL2.0,LGPL2.1}"
//        }
//    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

kotlin {
    android()
    jvm("desktop") {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":shared"))

                api(compose.foundation)
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material)
                api(compose.materialIconsExtended)
                api(compose.ui)
                api(compose.uiTooling)

                val coreVersion = "3.2.2"
                // Koin Core features
                implementation("io.insert-koin:koin-core:$coreVersion")

//                api("com.alialbaali.kamel:kamel-image:0.4.0")
            }
        }
        val commonTest by getting
        val androidMain by getting {
            dependencies {
                implementation("io.coil-kt:coil-compose:2.1.0")
            }
        }
        val desktopMain by getting
    }
}