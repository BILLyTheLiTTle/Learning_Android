package learing.android.signupsignin.persistence

/**
 * Pretends to be  a local storage class. It stores but not in the local storage, it stores in variables!!!
 */
object LocalStoragePretender {

    // Don't store cleartext password. Here, it exists to understand the example better.
    var clearTextPassword = ""
    var encryptedPassword = ByteArray(1)
    var initialiazationVector = ByteArray(1)
}