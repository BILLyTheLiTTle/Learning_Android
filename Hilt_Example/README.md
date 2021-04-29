# Hilt DI Example

## Learning Outcomes
In this application the target is to use:
- Hilt in a simple application
- A multi-module project
- Clean Architecture
- Reactive Programming
- Data Binding
- MVVM Pattern
- Variants and Flavours
- Tests (~~I will~~, I should)
- This app to read the weather you see, out of your window!

## Details
### Hilt
- A good [article](https://dagger.dev/hilt/gradle-setup.html) to **add Hilt dependencies**.
- When you step into the problem of not fimnding kapt(), the [answer](https://stackoverflow.com/questions/52580584/could-not-find-method-kapt-for-glide) from Martin Zeitler for the **kapt plugin** would be very helpful.
- A quick [video](https://www.youtube.com/watch?v=KI3L6d6Sm3Q) for **@Provides** and **@Binds** and how to inject **String**s in constructors.

### Multi-module project
- For multi-module project make sure you create the module as described [here](https://stackoverflow.com/questions/32419621/can-we-have-multiple-apps-in-one-android-studio-project) by Stephan Henningsen but not forget to use **Android Library** option instead of **Phone & Table Module**.

### Hilt Useful Links
1. A short [video](https://www.youtube.com/watch?v=B56oV3IHMxg) about the basics of Hilt usage in app and testing.
2. A [guide](https://dagger.dev/hilt/) which shows in detail what the above video speaks about (apart from testing).
3. A short and informative [article](https://medium.com/androiddevelopers/a-pragmatic-guide-to-hilt-with-kotlin-a76859c324a1) which writes about what the above video speaked (apart from testing)
4. A short [description](https://developer.android.com/training/dependency-injection/hilt-android) by Google about the modules and injection in Hilt.
5. [Migrating From Dagger to Hilt](https://www.raywenderlich.com/14212867-migrating-from-dagger-to-hilt)

### Clean Architecture
#### Theory article
[Here](https://proandroiddev.com/how-to-implement-a-clean-architecture-on-android-2e5e8c8e81fe) you can find very good theory about how Clean Architecture works. It lacks of example.

#### Code article
[Here](https://medium.com/android-dev-hacks/detailed-guide-on-android-clean-architecture-9eab262a9011) you can find a guide and an example of an attempt in applying clean architecture to Android. It contains DI also.

### Variants & Flavors
- A very simple [article](https://www.journaldev.com/21533/android-build-types-product-flavors).
- A very descriptive article [here](https://sgkantamani.medium.com/android-product-flavors-eb526e35f9f1). It is more than enough!

## Issues
No issues at the moment.

## Disclaimer
- Wheather data loads from [here](https://goweather.herokuapp.com/weather/Patras).
