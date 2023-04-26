package learning.android.dagger2example.di.modules

import dagger.Module
import dagger.Provides
import learning.android.dagger2example.di.ApplicationScope
import java.lang.StringBuilder
import javax.inject.Named

// Modules are a way to semantically encapsulate information on how to provide objects
@Module
class ContentApplicationModule {

    @Provides
    @ApplicationScope
    @Named("application_string")
    fun providesApplicationString() = StringBuilder("Application")

    @Provides
    @ApplicationScope
    @Named("application_int")
    fun providesApplicationInt() = StringBuilder("1")
}