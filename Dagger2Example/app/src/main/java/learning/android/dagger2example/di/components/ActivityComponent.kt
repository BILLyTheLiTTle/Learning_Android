package learning.android.dagger2example.di.components

import android.app.Activity
import dagger.BindsInstance
import dagger.Subcomponent
import learning.android.dagger2example.di.ActivityScope
import learning.android.dagger2example.di.modules.ContentActivityModule
import learning.android.dagger2example.di.modules.SubcomponentFragmentModule

@ActivityScope
@Subcomponent(modules = [SubcomponentFragmentModule::class, ContentActivityModule::class])
interface ActivityComponent {

    fun fragmentComponent(): FragmentComponent.Factory

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance activity: Activity): ActivityComponent
    }
}