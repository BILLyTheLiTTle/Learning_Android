package learning.android.dagger2example.di.modules

import dagger.Module
import learning.android.dagger2example.di.components.FragmentComponent

@Module(subcomponents = [FragmentComponent::class])
class SubcomponentFragmentModule {
}