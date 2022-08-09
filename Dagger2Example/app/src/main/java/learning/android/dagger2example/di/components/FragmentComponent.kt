package learning.android.dagger2example.di.components

import androidx.fragment.app.Fragment
import dagger.BindsInstance
import dagger.Subcomponent
import learning.android.dagger2example.di.FragmentScope
import learning.android.dagger2example.di.modules.ContentFragmentModule
import learning.android.dagger2example.screen_one.FragmentOne
import learning.android.dagger2example.screen_one.FragmentTwo
import learning.android.dagger2example.screen_two.FragmentThree

@FragmentScope
@Subcomponent (modules = [ContentFragmentModule::class])
interface FragmentComponent {

    fun inject(fragmentOne: FragmentOne)
    fun inject(fragmentTwo: FragmentTwo)
    fun inject(fragmentThree: FragmentThree)

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance fragment: Fragment): FragmentComponent
    }
}