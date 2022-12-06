# KMM Example

## Learning Outcomes
In this application the target is to use:
- Use KMM for sharing the logic between Android/iOS
- Integrate **Ktor** for KMM network calls
- Integrate **Insert-Koin** for DI
- Integrate **SQLDelight** for DB functionality
- Implement **Unit Tests** with **Insert-Koin**

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
- The DB migration implementation used here is wrong. Please see the appropriate at Ch.10 of [Kotlin Multiplatform by Tutorials](https://www.amazon.com/Kotlin-Multiplatform-Tutorials-First-Platforms/dp/1950325628).
- This [guide](https://kotlinlang.org/docs/multiplatform-mobile-ktor-sqldelight.html#configure-sqldelight-and-implement-cache-logic) shows how to integrate **SQLDelight** into a KMM project. It is also a part of the official guide I mentioned at the start of ***Details*** section.
- As always `Flow`s could be in the game by following this [guide](https://cashapp.github.io/sqldelight/js_sqlite/coroutines/).
- Last but not least don't forget the [**SQLDelight *plugin***](https://plugins.jetbrains.com/plugin/8191-sqldelight) for the Android Studio to facilitate the whole process. It can be installed from the Marketplace of the IDE.
- This [response](https://stackoverflow.com/a/65211389) shows how to migrate the DB to a newer version. You can also do it through the code as it is shown [here](https://cashapp.github.io/sqldelight/multiplatform_sqlite/migrations/). Keep in mind that in every new `.sqm` file you have to create the table otherwise the query cannot be executed. See my SQL code in `1.sqm` file (`CREATE TABLE IF NOT EXISTS Settings (version TEXT NOT NULL);`). ***Every `.sqm` does not override the `.sq` file but you have to do the job again (such as creating table before altering it)***.

### DI framework documentation (Insert-Koin)
- This [article](https://medium.com/swlh/dependency-injection-with-koin-for-android-43dda4d800d1) seems to be a very descriptive **Koin** article. I only got the way about how to add Context instance to Koin container (`androidContext(this@KmmApplication)`), but it may used for more. *Keep an eye on this one for unit tests as well*.
- For unit tests with **Koin** keep an eye on this [article](https://insert-koin.io/docs/reference/koin-test/checkmodules) as well.
- [Here](https://insert-koin.io/docs/setup/koin) you can find all gradle dependencies about **Koin** that you may need.
- The official documentation is unbeatable at the time of this writing. See [here](https://insert-koin.io/docs/reference/koin-core/definitions) to understand the very basic things of **Koin** about modules but when it comes to `scoped` dependencies this [article](https://proandroiddev.com/understanding-android-scopes-with-koin-cfe6b60ca579) will do the job! One minor thing to notice, in the article about `scoped` dependencies, is that `scope<MyActivity>` does not scope anything to `MyActivity`, it just says that the scope of the dependencies will be found in the `MyActivity` class and there it is `override val scope : Scope by activityScope()`.
- Last but not least, don't forget the way I used `by inject()` in the `Repository` class in `shared` module at `commonMain` directory. I had to make the `Repository` to implement `KoinComponent` interface! This can be also viewed in this [article](https://johnoreilly.dev/posts/kotlinmultiplatform-koin/).

#### Unit tests with Koin
- Although it is difficult to gather all dependencies under `commonXXX` module I should try to. Because I will be able to validate the module integration through the `checkModules` as it is described at Ch.9 of [Kotlin Multiplatform by Tutorials](https://www.amazon.com/Kotlin-Multiplatform-Tutorials-First-Platforms/dp/1950325628) or as it is described [here](https://insert-koin.io/docs/reference/koin-test/checkmodules).
- This [answer](https://stackoverflow.com/a/67620830) shows how to use your mock into your modules. This helps with field injection in the actual class. In my example `Repostiory` class is injected in the test and **mocked `Database`** is retrieved from the Koin container instead of **real `Database`** class.
- The project contains `ksp` integration. I could not find any working example about `ksp` so I magically wrote the line by myself. Pay attention to the plugin declaration (`id("com.google.devtools.ksp") version "1.7.10-1.0.6"`) as it should match your Kotlin version.
- The mocking was achieved with MockK library as I mentioned [here](https://stackoverflow.com/a/74245709). I tried Mockative but with no success.

## Issues
Not so much options for libraries and lack of examples!

## Disclaimer
The render of the dog image is from a public [dog api](https://dog.ceo/api/breeds/image/random).