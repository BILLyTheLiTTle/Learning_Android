# Gradle Example

## Learning Outcomes
In this application the target is to:
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

## Details
This is just a clone of the [Hilt & MVVM & Data Binding & Clean Architecture & Reactive](Hilt_Example) example. I don't care about the code of the application because I want to focus on build file code.

## Issues
This example at the moment  does not contain an example about how to create a binary plugin. It only contains a script plugin example. You can find the difference between these two [here](https://stackoverflow.com/a/50567860).
<br/>I am satisfied with the script plugin  and I will not create a binary plugin example soon. <br/>A guide for the binary plugin implementation could be found [here](https://proandroiddev.com/gradlepluginandroidsdk-8a2494cbd238).

## Disclaimer
- The balloon.aar library is the [skydoves/Balloon](https://github.com/skydoves/Balloon), but I have downloaded it for demonstration purposes.
- Nothing more than already described in the [Hilt & MVVM & Data Binding & Clean Architecture & Reactive](Hilt_Example) example.
