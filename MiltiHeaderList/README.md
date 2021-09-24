# Double Sticky Header Example

## Learning Outcomes
In this application the target is to:
- Implement a `LazyColumn` with double sticky headers
- Implement indexed `Column` bond in a `LazyColumn`

## Details
I was trying to crate a simple `LazyColumn` to import it in an old fashion Android code using `AndroidView`.
But during development I changed my mind and decided to give it a try. This is the reason why there are any small commits in the VCS.<br/>
*Note to myself: Should I export these two files (`DoubleHeaderLazyColumn` and `IndexColumn`) and create a library?*

## Issues
The way it changes the top sticky header is not so smooth. I would prefer with an animation.
For indexed `Column` it should scroll to the `LazyColumn`'s item while dragging the index instead of scrolling and then clicking an item index.
