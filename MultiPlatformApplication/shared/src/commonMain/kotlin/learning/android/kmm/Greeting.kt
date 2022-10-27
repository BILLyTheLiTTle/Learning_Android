package learning.android.kmm

class Greeting(private val platform: Platform) {

    fun greeting(name: String): String {
        if (name.isNullOrEmpty()) {
            return ""
        }

        return "Hello $name, you are in ${platform.name}!"
    }
}