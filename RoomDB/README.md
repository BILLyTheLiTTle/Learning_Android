# Room SQLite Example

## Learning Outcomes
In this application the target is to use:
- Create DB
- Execute Queries to DB
- Execute queries affecting multiple tables

### Sources
- Add the dependencies as they are shown [here](https://stackoverflow.com/a/53800306/1392366). Don't forget to apply the `kapt` plugin as it shown [here](https://stackoverflow.com/a/69994623/1392366). 
If for any reason `kapt` or Java version fucks up the Gradle setup see [this](https://stackoverflow.com/a/68773393/1392366).
- For a quick example in Kotlin with Room see this [article](https://howtodoandroid.com/room-database-android/) and a very good one but in Java could be found [here](https://medium.com/mindorks/using-room-database-android-jetpack-675a89a0e942). I prefer the second one but keep an eye on the first one as well.

### Extra Sources
- A simple [guide](https://levelup.gitconnected.com/using-room-in-jetpack-compose-d2b6b674d3a5) for Room. Don't forget to include the ktx dependency, as well
- An really good [guide](https://svvashishtha.medium.com/using-room-with-hilt-cb57a1bc32f) for Room with Hilt.
- If you want to store objects to your DB, this [answer](https://stackoverflow.com/a/50452877) will show you how. But don't forget **In addition to option 1, type converters should also be declared on appdatabase -> @TypeConverters(ImagesConverters::class)** as it is mentioned in the comment of the answer!

## Issues
To be added later.

## Disclaimer
To be added later.