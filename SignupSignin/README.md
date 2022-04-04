# Sign up / Sign in Example

## Learning Outcomes
In this application the target is to use:
- Android Keystore
- Biometrics
- Encryption for persistence (Read and Save user password)

### Sources
- Although this project is more complete, this [answer](https://stackoverflow.com/a/50447410/1392366) is a great start and it helps you understand what you are trying to achive and how to do it.
- This [example](https://medium.com/acmvit/using-biometricprompt-to-implement-fingerprint-authentication-in-android-apps-ebd9681dc0fa) is very good to show how to implement biometric login for application.
- A short and descriptive [guide](https://arctouch.com/blog/cryptographic-keys-fingerprint-authentication-android/) for encryption with biometrics authentication.

## Issues
In order to keep the example short I wrote bad code. For the whole example keep any eye on `Encryption` class and the `showBiometricPromptLogin()` from `SignInActivity`.

## Disclaimer
For persistence(!) purposes a simple class has been used which is not using Local Storage. I want to keep the example as simple as possible to demonstrate encryption.
For the whole example the take home knowledge is the `Encryption` class and the `showBiometricPromptLogin()` from `SignInActivity`. The other code parts are ornaments!