package gr.sfhmmy.kotlin.workshop

import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {
    fun calculatingGreeting(age: Int, greeting: String): String {
        var greeting = greeting
        greeting = if (age < 5) {
            "Hello Kid"
        } else if (age < 18) {
            "Hello $greeting"
        } else if (age < 60) {
            "Hello Mr/Ms $greeting"
        } else {
            "Hello Grandma/Grandpa"
        }
        return greeting
    }
}
