# Compose Navigation Example

## Learning Outcomes
In this application the target is to use:
- Modular application (3 feature modules, navigation, app)
- Navigation module
- Nested Navigation
- Weird navigation flow (Home Screen -> Screen 1 -> Screen 1_1 -> back to Home Screen directly, etc)

**Nothing of the above work as it should. It is a very bad example of using a navigation module. The back pressed action in buggy (Screen A -> Screen B -> Back to Screen A -> you cannot be in Screen B again) because the state is the same (StateFlow has Screen B as it last value and LaunchedEffect cannot renter if the value has not changed). So difficult to implement in order to gain unit testing for navigation. I am not going to continue this project.**

### Sources
- https://medium.com/google-developer-experts/modular-navigation-with-jetpack-compose-fda9f6b2bef7
- https://medium.com/google-developer-experts/navigating-in-jetpack-compose-78c78d365c6a
- https://joebirch.co/android/modular-navigation-with-jetpack-compose/
- https://shortcut.io/tech/adding-animations-to-your-jetpack-compose-navigation/
- https://developer.android.com/jetpack/compose/navigation

## Issues
Nothing at the moment.

## Disclaimer
Nothing at the moment.