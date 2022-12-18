package learning.android.kmm

class JvmPlatform : Platform {
    override val name: String = "JVM Platform"
}

actual fun getPlatform(): Platform = JvmPlatform()
