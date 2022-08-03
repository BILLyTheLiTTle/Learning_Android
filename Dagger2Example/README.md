# Dagger2 Example

## Learning Outcomes
In this application the target is to use:
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

## Description (to be deleted at the end of development)
There will be an application with 2 `Activit`ies and 4 `Fragment`s. Each `Fragment` will show text like this:<br/>
String [Aplication@hex_value]: application_string_value<br/>
Int [Aplication@hex_value]: application_int_value<br/>
String [Activity@hex_value]: activity_string_value<br/>
Int [Activity@hex_value]: activity_int_value<br/>
String [FragmentOne@hex_value]: fragment_one_string_value<br/>
Int [FragmentOne@hex_value]: fragment_one_int_value<br/>
String [FragmentTwo@hex_value]: fragment_two_string_value<br/>
Int [FragmentTwo@hex_value]: fragment_two_int_value<br/>

## Details

## Issues
To be added later.

## Disclaimer
- Nothing to mention.