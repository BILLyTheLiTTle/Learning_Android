# Sign up / Sign in Example

## Learning Outcomes
In this application the target is to use:
- Use KMM for sharing the logic between Android/iOS
- Integrate **Ktor** for KMM network calls
- Integrate **Insert-Koin** for DI (*TODO*)
- Integrate **SQLDelight** for DB functionality (*TODO*)

### Sources
#How to start
- how to create the project (https://proandroiddev.com/kmm-quickstart-guide-7598527a2ab9)

#official documentation
- Some tiny code snippets from oficial documentation (https://kotlinlang.org/docs/multiplatform-connect-to-apis.html#rules-for-expected-and-actual-declarations)
the most important is the open keyword combined with expect keyword

#ktor related
- howto setup ktor (https://ktor.io/docs/getting-started-ktor-client-multiplatform-mobile.html#ktor-dependencies)

- how to apply kotlinx serialization (used by ktor) in gradle (https://github.com/Kotlin/kotlinx.serialization)
- more dependencies to achive to modify HttpResponse to your dto (https://ktor.io/docs/serialization-client.html).
- Don't forget that you need `implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")` as well. it is mentioned in the previous link but I wrote again just to make sure that I will pay attention and pull my hair out.

## Issues
Not so much options of libraries!

## Disclaimer
The render of the dog image is from a public [dog api](https://dog.ceo/api/breeds/image/random).