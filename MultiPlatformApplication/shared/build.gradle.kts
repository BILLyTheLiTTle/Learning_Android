plugins {
    kotlin("multiplatform")
    id("com.android.library")

    id("kotlin-multiplatform") // or 'kotlin' or 'kotlin-multiplatform' for multiplatform projects
    id("kotlinx-serialization")

    id("com.squareup.sqldelight")

//    id("com.google.devtools.ksp")
}

kotlin {
    android()
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    sourceSets {
        val ktorVersion = "2.1.2"
        val sqlDelightVersion = "1.5.3"
        val koinVersion = "3.2.0"
        val commonMain by getting {
            dependencies {
                with(Dependencies.Ktor) {
                    implementation(clientCore)
                    implementation(serializationJson)
                    implementation(contentNegotiation)
                }
//                implementation("io.ktor:ktor-client-core:$ktorVersion")
//                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
//                implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")

                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")

                implementation("com.squareup.sqldelight:runtime:$sqlDelightVersion")
                implementation("com.squareup.sqldelight:coroutines-extensions:$sqlDelightVersion")

                implementation("io.insert-koin:koin-core:$koinVersion")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))

                implementation("io.insert-koin:koin-test:$koinVersion")

                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")
                implementation("io.mockk:mockk-common:1.12.1")

                // Mockative seems to not working at the moment of this writing
                // io.mockative.NoSuchMockError: A mock for the type Database was not generated.
//                implementation("io.mockative:mockative:1.2.3")
//                ksp{
//                    implementation("io.mockative:mockative-processor:1.1.2")
//                }
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-android:$ktorVersion")

                implementation("com.squareup.sqldelight:android-driver:$sqlDelightVersion")

                implementation("io.insert-koin:koin-android:$koinVersion")
            }
        }
        val androidTest by getting {
            dependencies {
                // without this, it cannot find "every", "any" and some other functions
                implementation("io.mockk:mockk-jvm:1.13.2")
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)

            dependencies {
                implementation("io.ktor:ktor-client-darwin:$ktorVersion")

                implementation("com.squareup.sqldelight:native-driver:$sqlDelightVersion")
            }
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    namespace = "learning.android.kmm"
    compileSdk = 32
    defaultConfig {
        minSdk = 21
        targetSdk = 32
    }
}

sqldelight {
    database("AppDatabase") {
        packageName = "learning.android.kmm.db"
//        sourceFolders = listOf("sqldelight")
//        schemaOutputDirectory = file("learning.android.kmm.db")
//        migrationOutputDirectory = file("learning.android.kmm.db")
//        deriveSchemaFromMigrations = true
//        verifyMigrations = true
//        version = 2
    }
}