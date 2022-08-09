package learning.android.dagger2example.di.components

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import learning.android.dagger2example.di.ApplicationScope
import learning.android.dagger2example.di.modules.SubcomponentActivityModule
import learning.android.dagger2example.di.modules.ContentApplicationModule

@ApplicationScope
@Component (modules = [SubcomponentActivityModule::class, ContentApplicationModule::class])
interface ApplicationComponent {

    fun activityComponent(): ActivityComponent.Factory

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): ApplicationComponent
    }
}