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
import androidx.biometric.BiometricPrompt
import java.util.concurrent.Executors
import androidx.biometric.BiometricManager

class SignInActivity : AppCompatActivity() {

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        passwordEditText = findViewById(R.id.signin_password_editText)
        usernameEditText = findViewById(R.id.signin_username_editText)

        showBiometricPromptLogin()
    }

    private fun showBiometricPromptLogin() {
        // Create the biometrics callback
        val callback = object: BiometricPrompt.AuthenticationCallback() {
            override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                Log.e("Biometric", "Error code: " + errorCode + "error String: " + errString)
                super.onAuthenticationError(errorCode, errString)
            }

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                Log.i("Biometric", "onAuthenticationSucceeded")
                signIn(null) // null?! Bad way to achieve decryption of password
                super.onAuthenticationSucceeded(result)
            }

            override fun onAuthenticationFailed() {
                Log.e("Biometric", "Authentication Failed")
                super.onAuthenticationFailed()
            }
        }

        val biometricPrompt = BiometricPrompt(this, Executors.newSingleThreadExecutor(), callback)

        val biometricManager: BiometricManager = BiometricManager.from(this)

        // check if biometric hardware exists on device
        when (biometricManager.canAuthenticate()) {
            BiometricManager.BIOMETRIC_SUCCESS -> {
                val promptInfo = BiometricPrompt.PromptInfo.Builder()
                    .setTitle("Biometric Login")
                    .setSubtitle("Fingerprint Authentication")
                    .setDescription("Please place your finger on the sensor to unlock")
                    // You can have either this
//                    .setNegativeButtonText("Use app login")
                    // or this one. But not both!
                    .setDeviceCredentialAllowed(true)
                    .build()
                biometricPrompt.authenticate(promptInfo)
            }
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> Log.e("Biometric", "Biometric Authentication currently unavailable")
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> Log.e("Biometric", "Your device doesn't support Biometric Authentication")
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> Log.e("Biometric", "Your device doesn't have any fingerprint enrolled")
        }
    }

    fun signIn(view: View?) {// Nullable here?! I have to admit, another bullshit code to keep the example as short as possible!
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

        Log.v("SignIn", "Signing in result: $message")
    }

    /**
     * For a regular login application, decrypt is ok instead of decryptAndCheck.
     * You need to decrypt the local stored password and send it to the server.
     * the "andCheck" part of the function in for debugging purposes to make sure that
     * encryption/decryption is working properly.
     */
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

        Log.v("SignIn", "Signing in result: $message")
    }
}