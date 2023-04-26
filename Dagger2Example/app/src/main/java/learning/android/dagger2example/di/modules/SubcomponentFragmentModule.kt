package learning.android.dagger2example.di.modules

import dagger.Module
import learning.android.dagger2example.di.components.FragmentComponent

// This module is used to include FragmentComponent subcomponent (sub-graph)
@Module(subcomponents = [FragmentComponent::class])
class SubcomponentFragmentModule {
}