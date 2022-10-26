# Sign up / Sign in Example

## Learning Outcomes
In this application the target is to use:
- Use KMM for sharing the logic between Android/iOS
- Integrate **Ktor** for KMM network calls
- Integrate **Insert-Koin** for DI (*TODO*)
- Integrate **SQLDelight** for DB functionality (*TODO*)

## Details
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
*To be added...*

### DI framework documentation (Insert-Koin)
*To be added...*

## Issues
Not so much options for libraries!

## Disclaimer
The render of the dog image is from a public [dog api](https://dog.ceo/api/breeds/image/random).