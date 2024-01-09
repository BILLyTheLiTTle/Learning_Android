plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("com.google.protobuf") version "0.9.4"
}

android {
    namespace = "com.learning.persistence"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.learning.persistence"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

//    sourceSets.getByName("main").java.srcDirs("${protobuf.generatedFilesBaseDir}/main/javalite")
//    sourceSets.getByName("main").java.srcDirs("$projectDir/src/main/proto")
}

// Whole protobuf section was copied from here (https://github.com/zhaobozhen/LibChecker/blob/c0c3bc7c661fe45cc44d5c6ab0202764652e0b7e/app/build.gradle.kts#L197)
protobuf {
    protoc {
//        artifact = if (osdetector.os == "osx") {
            // support both Apple Silicon and Intel chipsets
            val arch = System.getProperty("os.arch")
            val suffix = if (arch == "x86_64") "x86_64" else "aarch_64"
//            "${libs.google.protobuf.protoc.get()}:osx-$suffix"
//        } else
//            libs.google.protobuf.protoc.get().toString()
    }
    plugins {
        // Optional: an artifact spec for a protoc plugin, with "grpc" as
        // the identifier, which can be referred to in the "plugins"
        // container of the "generateProtoTasks" closure.
//        id("grpc") {
//            artifact = if (osdetector.os == "osx")
//                "${libs.grpc.gen.get()}:osx-aarch_64"
//            else
//                libs.grpc.gen.get().toString()
//        }
        generateProtoTasks {
            all().forEach {
                it.builtins {
                    create("java") {
                        option("lite")
                    }
                }

//                it.plugins {
//                    create("grpc") {
//                        option("lite")
//                    }
//                }
            }
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.2")

    // Shared preferences DataStore dependencies
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // Protocol Buffer DataStore dependencies
    implementation("androidx.datastore:datastore:1.0.0")
    implementation("com.google.protobuf:protobuf-javalite:3.25.1")

}