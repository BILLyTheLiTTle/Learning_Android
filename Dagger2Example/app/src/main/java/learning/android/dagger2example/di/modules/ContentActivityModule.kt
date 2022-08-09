package learning.android.dagger2example.di.modules

import dagger.Module
import dagger.Provides
import learning.android.dagger2example.di.ActivityScope
import java.lang.StringBuilder
import javax.inject.Named

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