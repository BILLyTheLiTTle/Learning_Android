package learing.android.signupsignin.signin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import learing.android.signupsignin.R
import learing.android.signupsignin.encryption.Encryption
import learing.android.signupsignin.logging.Logger
import learing.android.signupsignin.persistence.LocalStoragePretender

class SignInActivity : AppCompatActivity() {

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        passwordEditText = findViewById(R.id.signin_password_editText)
        usernameEditText = findViewById(R.id.signin_username_editText)
    }

    fun signIn(view: View) {
        val username = usernameEditText.text.toString()
        val password = passwordEditText.text.toString()

//        encryptAndCheck(password, username)
        decryptAndCheck(password, username)

        Logger.verbose("SignIn", password, LocalStoragePretender.clearTextPassword, LocalStoragePretender.encryptedPassword.toString())
    }

    /**
     * At every single encryption, although you had entered the same clear text value, the encrypted value is different.
     * So, you cannot encrypt and check for equality between two encrypted passwords.
     */
    private fun encryptAndCheck(password: String, username: String) {
        val encryptedPassword = Encryption.encrypt(password, username)

        val message = if (encryptedPassword.second.contentEquals(LocalStoragePretender.encryptedPassword)) {
            "Success"
        } else {
            "Fail"
        }

        Log.v("SignIn", message)
    }

    private fun decryptAndCheck(password: String, username: String) {
        val message = try {
            val clearTextPassword = Encryption.decrypt(LocalStoragePretender.encryptedPassword, username)
            if (clearTextPassword == password) {
                "Success"
            } else {
                "Fail"
            }
        } catch (e: Exception) {
            "Fail: ${e.message.toString()}"
        }

        Log.v("SignIn", message)
    }
}