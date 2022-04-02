package learing.android.signupsignin.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import learing.android.signupsignin.R
import learing.android.signupsignin.encryption.Encryption
import learing.android.signupsignin.logging.Logger
import learing.android.signupsignin.persistence.LocalStoragePretender

class SignUpActivity : AppCompatActivity() {

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        passwordEditText = findViewById(R.id.signup_password_editText)
        usernameEditText = findViewById(R.id.signup_username_editText)
    }

    fun signUp(view: View) {
        val username = usernameEditText.text.toString()
        val password = passwordEditText.text.toString()

        val data = Encryption.encrypt(password, username)

        LocalStoragePretender.initialiazationVector = data.first
        LocalStoragePretender.encryptedPassword = data.second

        Logger.verbose("SignUp", password, LocalStoragePretender.clearTextPassword, LocalStoragePretender.encryptedPassword.toString())
    }
}