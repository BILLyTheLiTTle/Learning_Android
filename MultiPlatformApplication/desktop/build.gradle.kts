import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin(multiplatform)
    id(composePlugin) version Versions.desktop_compose_plugin
}

group = "learning.android.kmm.android"
version = "1.0.0"

kotlin {
    jvm {
        withJava()
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
    }
    sourceSets {
        val jvmMain by getting {
            kotlin.srcDirs("src/jvmMain/kotlin")
            dependencies {
                implementation(compose.desktop.currentOs)
                api(compose.runtime)
                api(compose.foundation)
                api(compose.material)
                api(compose.ui)
                api(compose.materialIconsExtended)

                implementation(Deps.napier)
                // Coroutines
                implementation(Deps.Coroutines.common)

                implementation(project(":shared"))
//                implementation(project(":shared-ui"))
            }
        }
    }
}

compose.desktop {
    application() {
        mainClass = "learning.android.kmm.android.MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "MPA"
            macOS {
                bundleID = "learning.android.kmm.android"
            }
        }
    }
}
