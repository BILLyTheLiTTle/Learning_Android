package learning.android.dagger2example.di.modules

import dagger.Module
import dagger.Provides
import learning.android.dagger2example.di.ActivityScope
import java.lang.StringBuilder
import javax.inject.Named

// Modules are a way to semantically encapsulate information on how to provide objects
@Module
class ContentActivityModule {
    @Provides
    @ActivityScope
    @Named("activity_string")
    fun providesActivityString() = StringBuilder("Activity")

    @Provides
    @ActivityScope
    @Named("activity_int")
    fun providesActivityInt() = StringBuilder("10")
}