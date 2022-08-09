package learning.android.dagger2example.di.modules

import dagger.Module
import learning.android.dagger2example.di.components.ActivityComponent

@Module(subcomponents = [ActivityComponent::class])
class SubcomponentActivityModule {
}