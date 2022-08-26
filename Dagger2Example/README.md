# Dagger2 Example

## Learning Outcomes
In this application the target is to:
- **[Ch.11, 12]** Use different `Subcomponent`s: *<sup>1</sup>
  - `ApplicationComponent`
  - `ActivityComponent` (`Subcomponent` of `ApplicationComponent)
  - `FragmentComponent` (`Subcomponent` of `ActivityComponent)
- **[Ch.11]** Use different `Scope`s(`ApplicationScope`, `ActivityScope`, `FragmentScope`)
- **[Ch.9]** Use Qualifiers (`@Named`) and create new annotation classes to submit qualifiers
- **[Ch.9]** Allow optional binding (`@BindsOptionalOf`) (*TODO*)
- **[Ch.8]** Break cyclic dependency with `Provider<T>` (*TODO*)
- **[Ch.8]** Use `@Provides` in object and in abstract and see the one file per `@Provides` function generated (*TODO*)
- **[Ch.10]** Build above components using `Component.Builder` and `Component.Factory` (with `@BindsInstance`) (*this example contains only Factory pattern*)
- **[Ch.13, 14]** Dagger multibinding for `ViewModel`s (*will not do*)*<sup>2</sup>

## Details
### Dagger
This example is based on [*Dagger by Tutorials* book](https://www.raywenderlich.com/books/dagger-by-tutorials/v1.0). That's why you see something like [Ch.X] next to each learning outcome.

If you want to read some theory real quick and with deep knowledge [Google's official guide](https://developer.android.com/training/dependency-injection/dagger-android) is superb.

*<sup>1</sup>: Probably I should re work this example using `dependencies` instead of `Subcomponent`s but it is not my priority at the moment.

*<sup>2</sup>: Dagger multibinding will not be implemented because I have already worked on this during my work time so I don't feel that I need more experimenting on this one. This [article](https://betterprogramming.pub/injecting-android-viewmodels-with-dagger2-in-clean-architecture-744c1fe81530) is brilliant on showing how to work with simple **Injection** but this [article](https://medium.com/@adrianocelentano/dagger-multibindings-for-android-part-2-4e0c38d85449) explains the same concept (with superb theory) and moves it further in explaining `Map`s in Dagger2.

### Other
Although this example's purpose is to demonstrate Dagger's usage I cannot oversee that the `ViewBinding` with `<merge>` in xml needs an effort as well. More info [here](https://stackoverflow.com/a/60260556).

## Issues
To be added later.

## Disclaimer
- Nothing to mention.
