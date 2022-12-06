# Learning_Android
Various Android examples based on what comes to my mind!

## Table of Contents

### 1. [Navigation Graph & Bottom Navigation Bar & MVI & Data Binding](BottomNav_MVI_NavGraph)
- Use Bottom Navigation Bar
- Fragments
- Navigation Graph
- LiveData
- Data Binding (one-way & two-way)
- Model-View-ViewModel design pattern
- Unit Testing
- Instrumented Testing

### 2. [Hilt & MVVM & Data Binding & Clean Architecture & Reactive](Hilt_Example)
- Hilt in a simple application
- A multi-module project
- Clean Architecture
- Reactive Programming
- Data Binding
- MVVM Pattern
- Variants & Flavours
- Unit Testing
- Instrumented Testing

### 3. [Simple Workshop Projects](Simple_Workshop_Projects)
- Use these projects in a basic level workshop
- Explain and apply clean code  techniques
- Simple Unit testing cases
- Simple instrumented testing cases
- Test and debug any issues found in this projects

### 4. [MVI With State Pattern](MVI_With_State_Pattern)
- Very simple UI (no effort to understand)
- Implement MVI pattern using OO State Design Pattern

### 5. [Jetpack Compose Example](JetpackComposeExample)
- Use Main UI Components (List, Tabs and Pager, Loading)
- Use MVI pattern (a possible implementation)

### 6. [Double Sticky Header Example](MiltiHeaderList)
- Not a perfect but a good implementation of a `LazyColumn` with double sticky headers
- It also shows an implementation of indexed `Column` bond in a `LazyColumn`

### 7. [Request Permissions](Permissions_Application)
- Request permission
- Keep track of granted/denied/never ask again permissions

### 8. [Dog Breeds](DogBreeds)
- Jetpack Compose
- Jetpack Compose Navigation
- Jetpack Paging Library
- Kotlin Coroutines
- Kotlin Flow (StateFlow, SharedFlow)
- Dependency Injection (Hilt)
- Retrofit
- Clean architecture
- Testing (Unit, Coroutines Unit, UI Testing, MockWebServer)

### 9. [Room SQLite Example](RoomDB)
- Create DB
- Execute simple queries to DB
- Experiment with DB relationship from code and avoid lengthy queries

### 10. [Sign up / Sign in](SignupSignin)
- Android Keystore
- Biometrics
- Encryption for persistence (Read and Save user password)

### 11. [Gradle Example](Gradle_Example)
- Insert libraries:
  - From local folder
  - From local repository
- Create a sample task to show the difference between configuration and execution phase
- Create a custom task to rename output file per flavor
- Modify the above custom task to:
  - A new binary plugin (**TODO**)
  - A new script plugin
- Create new tasks dynamically
- Run gradle tasks (dependsOn, mustRunAfter doLast, doFirst) and use attributes in command line (profiling)
- Gradle Android properties (split apk, manual shrinking)

### 12. [Jetpack Compose Navigation](ComposeNavigation)(**Very Bad example of using a navigation module.**)
- Modular application (3 feature modules, navigation, app)
- Navigation module
- Nested Navigation
- Weird navigation flow (Home Screen -> Screen 1 -> Screen 1_1 -> back to Home Screen directly, etc)

### 13. [Dagger2 Example](Dagger2Example)
- **[Ch.11, 12]** Use different `Subcomponent`s:
  - `ApplicationComponent`
  - `ActivityComponent` (`Subcomponent` of `ApplicationComponent)
  - `FragmentComponent` (`Subcomponent` of `ActivityComponent)
- **[Ch.11]** Use different `Scope`s(`ApplicationScope`, `ActivityScope`, `FragmentScope`)
- **[Ch.9]** Use Qualifiers (`@Named`) and create new annotation classes to submit qualifiers
- **[Ch.9]** Allow optional binding (`@BindsOptionalOf`) (*TODO*)
- **[Ch.8]** Break cyclic dependency with `Provider<T>` (*TODO*)
- **[Ch.8]** Use `@Provides` in object and in abstract and see the one file per `@Provides` function generated (*TODO*)
- **[Ch.10]** Build above components using `Component.Builder` and `Component.Factory` (with `@BindsInstance`) (this example contains only Factory pattern)
- **[Ch.13, 14]** Dagger multibinding for `ViewModel`s (*will not do*)

### 14. [KMM Example](MultiPlatformApplication)
- Use KMM for sharing the logic between Android/iOS
- Use `buildSrc` module
- Integrate **Ktor** for KMM network calls
- Integrate **Insert-Koin** for DI
- Integrate **SQLDelight** for DB functionality
- Implement **Unit Tests** with **Insert-Koin**

### No. [Project_Name](Project_Path)
- Use Tabbed Activity
- Fragments
- Navigation Graph
- Model-View-Intent architecture using [Uniflow](https://github.com/uniflow-kt/uniflow-kt/blob/master/Documentation.md)
- Dagger 2
- Unit Testing
- Instrumented Testing
