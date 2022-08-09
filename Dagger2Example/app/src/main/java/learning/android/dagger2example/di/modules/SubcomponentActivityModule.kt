package learning.android.dagger2example.di.modules

import dagger.Module
import learning.android.dagger2example.di.components.ActivityComponent

// This module is used to include ActivityComponent subcomponent
@Module(subcomponents = [ActivityComponent::class])
class SubcomponentActivityModule {
}