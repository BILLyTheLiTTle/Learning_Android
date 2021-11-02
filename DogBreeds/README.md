# Dog Breeds

## Learning Outcomes
In this application the target is to use:
- Jetpack Compose
- Jetpack Compose Navigation
- Jetpack Paging Library
- Kotlin Coroutines
- Kotlin Flow (StateFlow, SharedFlow)
- Dependency Injection (Hilt)
- Retrofit
- Clean architecture
- Testing (Unit, Coroutines Unit, Espresso)

## Details
Apart from the notes used for **Hilt_Example** and **JetpackComposeExample** projects, the following sources provided extreme help:
- This [article](https://www.geeksforgeeks.org/kotlin-flow-in-android-with-example/) provided a good explanation about the result object from network and basic handling of the `Flow`s in the `ViewModel` class.    
- This [article](https://proandroiddev.com/livedata-vs-sharedflow-and-stateflow-in-mvvm-and-mvi-architecture-57aad108816d) is incredible in explaining how to:
  - Cancel a `Flow` properly (from `onStart`/`onStop` and using `LifecycleOwner`, as well)
  - Use `StateFlow` for parsing *States* to UI
  - Use `SharedFlow` for parsing *Events* from UI.
- This [article](https://medium.com/androiddevelopers/migrating-from-livedata-to-kotlins-flow-379292f419fb) shows the best way to `collect` (using `repeatOnLifecycle`) a `StateFlow` and avoiding any memory leaks by canceling. I was able to understand the transition from `LiveData` to `StateFlow` (check `OPTION 2` in `learning.android.dogbreeds.ui.screens.breeds.list.BreedsListViewModel`).

## Issues
To be added later.

## Disclaimer
- Dog data load from [here](https://documenter.getpostman.com/view/4016432/the-dog-api/RW81vZ4Z#26bd3f92-dd58-4569-bc13-22fa76396fe8).