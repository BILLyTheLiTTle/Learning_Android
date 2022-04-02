package learing.android.signupsignin.encryption

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import learing.android.signupsignin.persistence.LocalStoragePretender
import java.lang.Exception
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec

/**
 * At this example we encrypt the password of the user.
 * This is the most usual use case.
 * Instead of the password we could encrypt other sensitive data as well.
 */
class Encryption {
    companion object {
        private const val KEYSTORE_PROVIDER = "AndroidKeyStore"

        private fun generateKey(alias: String): SecretKey {
            val keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, KEYSTORE_PROVIDER)
            val spec = KeyGenParameterSpec
                .Builder(alias, KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT)
                .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                .build()

            keyGenerator.init(spec)
            return keyGenerator.generateKey()
        }

        private fun getSecretKey(alias: String): SecretKey {
            val keyStore = KeyStore.getInstance(KEYSTORE_PROVIDER).apply { load(null) }
            val secretKeyEntry = keyStore.getEntry(alias, null) //as KeyStore.SecretKeyEntry

            return if (secretKeyEntry != null) {
                (secretKeyEntry as KeyStore.SecretKeyEntry).secretKey
            } else {
                generateKey(alias)
            }
        }

        private fun getCipher() = Cipher.getInstance("AES/GCM/NoPadding")

        fun encrypt(text: String, username: String): Pair<ByteArray, ByteArray> {
            val cipher = getCipher()
            val secretKey = getSecretKey(username)
            cipher.init(Cipher.ENCRYPT_MODE, secretKey)
            val iv = cipher.iv
            val encryptedText = cipher.doFinal(text.toByteArray())

            return Pair(iv, encryptedText)
        }

        @Throws(Exception::class)
        fun decrypt(encrypted: ByteArray, username: String): String {
            if (LocalStoragePretender.initialiazationVector.size == 1 || LocalStoragePretender.encryptedPassword.size == 1) {
                // the user tries to decrypt although the user has never encrypted anything
                throw Exception("Are you sure you have signed up already?")
            }

            val cipher = getCipher()
            val secretKey = getSecretKey(username)
            val spec = GCMParameterSpec(128, LocalStoragePretender.initialiazationVector)
            cipher.init(Cipher.DECRYPT_MODE, secretKey, spec)
            val decoded = cipher.doFinal(encrypted)
            val text = String(decoded, Charsets.UTF_8)

            return  text
        }
    }
}