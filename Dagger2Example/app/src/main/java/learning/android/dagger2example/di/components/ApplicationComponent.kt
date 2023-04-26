package learning.android.dagger2example.di.components

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import learning.android.dagger2example.di.ApplicationScope
import learning.android.dagger2example.di.modules.SubcomponentActivityModule
import learning.android.dagger2example.di.modules.ContentApplicationModule

// The interface that generates the graph is annotated with @Component
@ApplicationScope
@Component (modules = [SubcomponentActivityModule::class, ContentApplicationModule::class])
interface ApplicationComponent {

    // This function exposes the ActivityComponent Factory out of the graph so consumers
    // can use it to obtain new instances of ActivityComponent
    fun activityComponent(): ActivityComponent.Factory

    // Factory that is used to create instances of this component
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): ApplicationComponent
    }
}