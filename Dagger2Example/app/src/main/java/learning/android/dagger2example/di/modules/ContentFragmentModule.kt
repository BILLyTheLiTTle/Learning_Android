package learning.android.dagger2example.di.modules

import dagger.Module
import dagger.Provides
import learning.android.dagger2example.di.FragmentScope
import java.lang.StringBuilder
import javax.inject.Named

// Modules are a way to semantically encapsulate information on how to provide objects
@Module
class ContentFragmentModule {
    @Provides
    @FragmentScope
    @Named("fragment_string")
    fun providesFragmentString() = StringBuilder("Fragment")

    @Provides
    @FragmentScope
    @Named("fragment_int")
    fun providesFragmentInt() = StringBuilder("100")
}