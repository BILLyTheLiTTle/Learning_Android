# Dog Breeds

## Learning Outcomes
In this application the target is to use:
- Jetpack Compose
- Jetpack Compose Animation -> **TODO**
- Jetpack Compose Navigation ->
- Jetpack Paging Library 3
- Kotlin Coroutines
- Kotlin Flow (Flow, StateFlow, SharedFlow) -> **TODO** In details to next dog (with network request id=1) with SharedFlow
- Dependency Injection (Hilt)
- Retrofit
- Room Database -> **TODO**
- Clean architecture
- Testing (Unit, Coroutines Unit, Espresso) -> **TODO**

## Details
Apart from the notes used for **Hilt_Example** and **JetpackComposeExample** projects, the following sources provided extreme help:
- This [article](https://www.geeksforgeeks.org/kotlin-flow-in-android-with-example/) provided a good explanation about the result object from network and basic handling of the `Flow`s in the `ViewModel` class.    
- This [article](https://proandroiddev.com/livedata-vs-sharedflow-and-stateflow-in-mvvm-and-mvi-architecture-57aad108816d) is incredible in explaining how to:
  - Cancel a `Flow` properly (from `onStart`/`onStop` and using `LifecycleOwner`, as well)
  - Use `StateFlow` for parsing *States* to UI
  - Use `SharedFlow` for parsing *Events* from UI.
- This [article](https://medium.com/androiddevelopers/migrating-from-livedata-to-kotlins-flow-379292f419fb) shows the best way to `collect` (using `repeatOnLifecycle`) a `StateFlow` and avoiding any memory leaks by canceling. I was able to understand the transition from `LiveData` to `StateFlow` (check `OPTION 2` in `learning.android.dogbreeds.ui.screens.breeds.list.BreedsListViewModel`).

### Color sources
- If you ever have any problem about color naming yu should visit the links mentioned [here](https://proandroiddev.com/naming-conventions-colors-xml-android-8f89139f1056).
It is a good start to define a descriptive color name!
- Also you can have nice color combinations [here](https://material.io/resources/color/#!/?view.left=0&view.right=0).

### Paging adapter
- A good [article](https://proandroiddev.com/infinite-lists-with-paging-3-in-jetpack-compose-b095533aefe6) about paging library.
- You also need to visit this [article](https://medium.com/simform-engineering/list-view-with-pagination-using-jetpack-compose-e131174eac8e) to see the implementation of `getRefreshKey()` inside the `PagingSource`. I believe that this implementation is wrong because it creates issues with the refreshing of the data. See my implementation in code instead.

### Naigation
Apart from the articles mentioned in **JetpackComposeExample** see this [article](https://proandroiddev.com/jetpack-compose-navigation-architecture-with-viewmodels-1de467f19e1c), as well.

## Issues
To be added later.

## Disclaimer
- Dog data load from [here](https://documenter.getpostman.com/view/4016432/the-dog-api/RW81vZ4Z#26bd3f92-dd58-4569-bc13-22fa76396fe8).