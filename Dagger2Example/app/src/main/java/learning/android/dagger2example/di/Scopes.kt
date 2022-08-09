package learning.android.dagger2example.di

import javax.inject.Scope

/*
When a type is marked with a scope annotation, it can only be used by components that are annotated with the same scope.
If a type is marked with an annotation then dagger provides the same instance of this object (does not create a new one)

When a component is marked with a scope annotation, it can only provide types with that annotation or types that have no annotation.

A subcomponent cannot use a scope annotation used by one of its parent components.
 */

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ApplicationScope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ActivityScope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class FragmentScope