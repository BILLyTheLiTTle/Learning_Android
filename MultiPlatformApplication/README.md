# Sign up / Sign in Example

## Learning Outcomes
In this application the target is to use:
- Use KMM for sharing the logic between Android/iOS
- Integrate **Ktor** for KMM network calls
- Integrate **Insert-Koin** for DI (*TODO*)
- Integrate **SQLDelight** for DB functionality (*TODO*)

## Details
Probably this [guide](https://kotlinlang.org/docs/multiplatform-mobile-ktor-sqldelight.html#make-the-business-logic-cross-platform) should be the most complete guide I have seen. Keep an eye on it because it will be updated as it from official Kotlin documentation.

### How to start
This [article](https://proandroiddev.com/kmm-quickstart-guide-7598527a2ab9) is amazing about how to start a new KMM project.

### Official documentation
[Here](https://kotlinlang.org/docs/multiplatform-connect-to-apis.html#rules-for-expected-and-actual-declarations) you can find code snippets for KMM. The most important code snippet is the one which demonstrates the usage of `open` keyword combined with `expect`.

### Network documentation (Ktor)
The official documentation for **Ktor** is unbeatable. Actually there are not many **Ktor** examples out the there so you have to work with this one, but thankfully they have done an amazing job and it is really helpful. Here are the sources needed for this example:
- [**Ktor** setup](https://ktor.io/docs/getting-started-ktor-client-multiplatform-mobile.html#ktor-dependencies).
- [**kotlinX serialization** (*used by Ktor*) gradle setup](https://github.com/Kotlin/kotlinx.serialization).
- [Modify the `HttpResponse` to your appropriate DTO object dependencies and example](https://ktor.io/docs/serialization-client.html). *Don't forget that you need `implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")` as well. Tt is mentioned in the previous link but I wrote again just to make sure that I will pay attention and avoid pulling my hair out!*

### DB documentation (SQLDelight)
- This [guide](https://kotlinlang.org/docs/multiplatform-mobile-ktor-sqldelight.html#configure-sqldelight-and-implement-cache-logic) shows how to integrate **SQLDelight** into a KMM project. It is also a part of the official guide I mentioned at the start of ***Details*** section.
- As always `Flow`s could be in the game by following this [guide](https://cashapp.github.io/sqldelight/js_sqlite/coroutines/).
- Last but not least don't forget the [**SQLDelight *plugin***](https://plugins.jetbrains.com/plugin/8191-sqldelight) for the Android Studio to facilitate the whole process. It can be installed from the Marketplace of the IDE.

### DI framework documentation (Insert-Koin)
*To be added...*

## Issues
Not so much options for libraries!

## Disclaimer
The render of the dog image is from a public [dog api](https://dog.ceo/api/breeds/image/random).