# Double Sticky Header Example

## Learning Outcomes
In this application the target is to:
- Implement a `LazyColumn` with double sticky headers

## Details
I was trying to crate a simple `LazyColumn` to import it in an old fashion Android code using `AndroidView`.
But during development I changed my mind and decided to give it a try. This is the reason why there are any small commits in the VCS.
*Note to myself: Should I export some modifiers and `Composable` lambdas and create a library?*

## Issues
The way it changes the top sticky header is not so smooth. I would prefer with an animation.

## Disclaimer
- [Details](https://en.wikipedia.org/wiki/State_pattern) for OO State Design Pattern.
- Obviously, I could not understand reading only from Wikipedia and I had to read the appropriate chapter in [Head First Design Patterns](https://www.amazon.com/Head-First-Design-Patterns-Object-Oriented/dp/149207800X).
