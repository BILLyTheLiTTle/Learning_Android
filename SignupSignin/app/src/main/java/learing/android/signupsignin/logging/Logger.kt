package learing.android.signupsignin.logging

import android.util.Log

object Logger {
    fun verbose(label: String, uiPassword: String, storedClearPassword: String, encryptedStoredPassword: String) {
        Log.v(label, "UI Password -> $uiPassword")
        Log.v(label, "Stored Clear Text Password -> $storedClearPassword")
        Log.v(label, "Stored Encrypted Password -> $encryptedStoredPassword")
    }
}