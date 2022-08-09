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

    // This function exposes the FragmentComponent Factory out of the graph so consumers
    // can use it to obtain new instances of FragmentComponent
    fun fragmentComponent(): FragmentComponent.Factory

    // Factory that is used to create instances of this subcomponent
    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance activity: Activity): ActivityComponent
    }
}