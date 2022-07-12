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

### 13. [Dagger2 Example](Dagger2_Example)
_Description_: <br/>
There will be an application with 1 `Activity` and 2 `Fragments`. Each `Fragment` will show text like this:<br/>
Aplication String: application_string_value<br/>
Aplication Int: application_int_value<br/>
Activity String: activity_string_value<br/>
Activity Int: activity_int_value<br/>
Fragment 1 String: fragment_one_string_value<br/>
Fragment 1 Int: fragment_one_int_value<br/>
Fragment 2 String: fragment_two_string_value<br/>
Fragment 2 Int: fragment_two_int_value<br/>
All Fragments/Activities will have a String with `@BindsInstance` or/and the class name to show the id to verify its scope<br/>
**_== TODO_: Delete the  above description after the app implementation ==**

- **[C.11, 12]** Use different `Component`s with dependencies
  - `AppComponent`
  - `ActivityComponent`(with `@Subcomponent` -> `MyActivitySubcomponent`) 
  - `FragmentComponent`(with `@Subcomponent`s -> `MyFragmentOneSubComponent`, `MyFragmentTwoSubcomponent`)
- **[C.11]** Use different `Scope`s(`ApplicationScope`, `ActivityScope`, `FragmentScope`)
- **[C.9]** Use Qualifiers (`@Named`) and create new annotation classes to submit qualifiers
- **[C.9]** Allow optional binding (`@BindsOptionalOf`)
- **[C.8]** Break cyclic dependency with `Provider<T>`
- **[C.8]** Use `@Provides` in object and in abstract and see the one file per `@Provides` function generated
- **[C.10]** Build above components using `Component.Builder` and `Component.Factory` (with `@BindsInstance`)
- **[C.13, 14]** Dagger multibinding for `ViewModel`s
- Add more if needed

### No. [Project_Name](Project_Path)
- Use Tabbed Activity
- Fragments
- Navigation Graph
- Model-View-Intent architecture using [Uniflow](https://github.com/uniflow-kt/uniflow-kt/blob/master/Documentation.md)
- Dagger 2
- Unit Testing
- Instrumented Testing
