# Dog Breeds

## Learning Outcomes
In this application the target is to use:
- Jetpack Compose
  - Layouts (`LazyColumn`, `Column`, `Row`, `Box`, `ConstraintLayout`)
  - Effects (`LaunchedEffect`)
  - Navigation
  - Animation (`AnimatedVisibility`, animate `AnimatedVisibility` children separately, `animateContentSize`, `updateTransition`)
  - Gestures (Swipe left/right)
  - Various (`Modifier`, `Button`, etc)
  - Optimization technique (it does not exist in the code of this example)
- Jetpack Compose Animation
- Jetpack Paging Library 3
- Kotlin Coroutines
- Kotlin Flow (Flow, StateFlow, SharedFlow)
- Dependency Injection (Hilt)
- Retrofit
- Room Database (Dao, TypeConverters)
- Clean architecture
- Testing -> **TODO test every module with 95% coverage**
  - Instrumentation (Hilt, Semantics, wait until UI is ready)
  - Unit (Flow, Coroutines, Mock, MockWebServer)

## Details
Apart from the notes used for **Hilt_Example** and **JetpackComposeExample** projects, the following sources provided extreme help:
- This [article](https://www.geeksforgeeks.org/kotlin-flow-in-android-with-example/) provided a good explanation about the result object from network and basic handling of the `Flow`s in the `ViewModel` class.    
- This [article](https://proandroiddev.com/livedata-vs-sharedflow-and-stateflow-in-mvvm-and-mvi-architecture-57aad108816d) is incredible in explaining how to:
  - Cancel a `Flow` properly (from `onStart`/`onStop` and using `LifecycleOwner`, as well)
  - Use `StateFlow` for parsing *States* to UI
  - Use `SharedFlow` for parsing *Events* from UI.
- This [article](https://medium.com/androiddevelopers/migrating-from-livedata-to-kotlins-flow-379292f419fb) shows the best way to `collect` (using `repeatOnLifecycle`) a `StateFlow` and avoiding any memory leaks by canceling. I was able to understand the transition from `LiveData` to `StateFlow` (check `OPTION 2` in `learning.android.dogbreeds.ui.screens.breeds.details.BreedDetailsViewModel`).

### Color sources
- If you ever have any problem about color naming you should visit the links mentioned [here](https://proandroiddev.com/naming-conventions-colors-xml-android-8f89139f1056).
It is a good start to define a descriptive color name!
- Also you can have nice color combinations [here](https://material.io/resources/color/#!/?view.left=0&view.right=0).

### Paging adapter
- A good [article](https://proandroiddev.com/infinite-lists-with-paging-3-in-jetpack-compose-b095533aefe6) about paging library.
- You also need to visit this [article](https://medium.com/simform-engineering/list-view-with-pagination-using-jetpack-compose-e131174eac8e) to see the implementation of `getRefreshKey()` inside the `PagingSource`. I believe that this implementation is wrong because it creates issues with the refreshing of the data. See my implementation in code instead.

### Optimization techniques
- One important optimazation is with `derivedStateOf`. It has lazy initialization which makes the first run faster. It will trigger recomposition if the content of the block produces different result. It can be combined with `remember{}` when the code in its block are `val/var` instead of states. More info could be found [here](https://www.bontouch.com/case/state-and-derived-state-in-compose/).
- A lot of optimization techniques could be found [here](https://skyyo.medium.com/performance-in-jetpack-compose-9a85ce02f8f9).
- Another optimization technique is to use `@Immutable` and `@Stable` annotations to skip recompositions whenever possible. A very descriptive article is [this one](https://blog.shreyaspatil.dev/promise-compose-compiler-and-imply-when-youll-change) and [that one](https://betterprogramming.pub/optimizing-recomposition-in-jetpack-compose-stability-system-f8ec0c92de33) as well

### Navigation
Apart from the articles mentioned in **JetpackComposeExample** see this [article](https://proandroiddev.com/jetpack-compose-navigation-architecture-with-viewmodels-1de467f19e1c), as well.

### Room
- A simple [guide](https://levelup.gitconnected.com/using-room-in-jetpack-compose-d2b6b674d3a5) for Room. Don't forget to include the ktx ependency, as well
- An really good [guide](https://svvashishtha.medium.com/using-room-with-hilt-cb57a1bc32f) for Room with Hilt.
- If you want to store objects to your DB, this [answer](https://stackoverflow.com/a/50452877) will show you how. But don't forget **In addition to option 1, type converters should also be declared on appdatabase -> @TypeConverters(ImagesConverters::class)** as it is mentioned in the comment of the answer!

### Testing
- This [answer](https://stackoverflow.com/a/38345579/1392366) is helpful if you want to create a folder (ex. `resource`) for testing purposes.
#### Instrumentation Tests
- A very simple [article](https://levelup.gitconnected.com/testing-in-compose-d09b59337e4e) which shows basic testing examples and screenshot testing.
- An amazing [article](https://medium.com/nerd-for-tech/writing-an-integration-test-with-jetpack-compose-and-dagger-hilt-8ef888c1a23d) with details about Jetpack Compose Intergation with Hilt:
  - `@Module` for fake list provider
  - Test class implemenenting `AndroidJUnitRunner`
  - `@Rule`s ordering
  - Shows how to get viewmodel in test class by using (`createAndroidComposeRule<MyActivity>()`)

#### Unit Tests
- This [article](https://fabiosanto.medium.com/unit-testing-coroutines-state-flow-c6e6de580027) contains simple example about how to test coroutine which returns a `StateFlow`.
- This [answer](https://stackoverflow.com/a/57958441/1392366) will help you fix any mocking issues to calls to Android platform(ex. `Log.d()`, etc).
- If you need any help about mocking objects which contains initialization in variables (through `init{}`, or directly such us `var shouldGetLocalData = true`) don't forget to `relax` the mock like is described [here](https://stackoverflow.com/questions/61151905/io-mockk-mockkexception-no-answer-found-for-savedstatehandle1-setkey-some).
- `MockWebServer` is a way to mock server responses instead of mocking the model of the response. You could make a server to response with a 404 code and then you could check if the actual model is contructed correctly
  - *(a)\** A very simple [guide](https://medium.com/mobile-app-development-publication/android-mock-server-for-unittest-82f5bbbf0362) which has examples of `MockWebServer`.
  - *(b)\** A simple [example](https://medium.com/mobile-app-development-publication/android-reading-a-text-file-during-test-2815671e8b3b) which shows how to read sample json response from file.
  - Although my source is simple enough I should try to follow this [guide](https://www.bloco.io/blog/mocking-retrofit-api-responses-with-mockwebserver-hilt) to use specific Hilt modules for testing.
- Unit testing with Coroutines version 1.6.X:
  - See the [migration guide](https://github.com/Kotlin/kotlinx.coroutines/blob/master/kotlinx-coroutines-test/MIGRATION.md) but mainly pay attention to one paragraph before and after the [bold title](
https://github.com/Kotlin/kotlinx.coroutines/blob/master/kotlinx-coroutines-test/MIGRATION.md#other-considerations).
  - Refresh your memory with the difference between CoroutineScope and CoroutineContext with the responses [here](https://stackoverflow.com/questions/54416840/kotlin-coroutines-scope-vs-coroutine-context).
  - A short [article](https://medium.com/@ralf.stuckert/testing-coroutines-update-1-6-0-701d53546683) that describes most of migration important aspects. Every example he show is not working, but in my tests in `BreedDetailsViewModelTest` you can find what is working, for sure.

*\*(a), (b) :  The source of these could be found [here](https://github.com/elye/demo_android_mock_web_service). You actually need to read the [Chat](https://github.com/elye/demo_android_mock_web_service/blob/master/app/src/main/java/com/example/mockserverexperiment/Chat.kt) class and [ChatTest](https://github.com/elye/demo_android_mock_web_service/blob/master/app/src/test/java/com/example/mockserverexperiment/ChatTest.kt) class.*

#### Paging Library 3 Tests
- This is an [article](https://medium.com/@mohamed.gamal.elsayed/android-how-to-test-paging-3-pagingsource-433251ade028) about Paging Library 3 testing but I think that my way is much simpler.

## Issues
To be added later.

## Disclaimer
- Dog data load from [here](https://documenter.getpostman.com/view/4016432/the-dog-api/RW81vZ4Z#26bd3f92-dd58-4569-bc13-22fa76396fe8).
