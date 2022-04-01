package learing.android.signupsignin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.content.Intent
import learing.android.signupsignin.signin.SignInActivity
import learing.android.signupsignin.signup.SignUpActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun goToSignUpScreen(view: View) {
        startActivity(Intent(this, SignUpActivity::class.java))
    }

    fun goToSignInScreen(view: View) {
        startActivity(Intent(this, SignInActivity::class.java))
    }
}