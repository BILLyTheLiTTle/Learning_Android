object Dependencies {
    object Compose {
        const val ui = "androidx.compose.ui:ui:${Versions.ui}"
        const val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.ui}"
        const val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview:${Versions.ui}"
        const val foundation = "androidx.compose.foundation:foundation:${Versions.ui}"
        const val material = "androidx.compose.material:material:${Versions.ui}"
        const val activity = "androidx.activity:activity-compose:${Versions.activity}"

        private object Versions {
            const val ui = "1.2.1"
            const val activity = "1.5.1"
        }
    }

    object Ktor {
        private const val version = "2.1.2"

        const val clientCore = "io.ktor:ktor-client-core:$version"
        const val serializationJson = "io.ktor:ktor-serialization-kotlinx-json:$version"
        const val contentNegotiation = "io.ktor:ktor-client-content-negotiation:$version"
    }
}