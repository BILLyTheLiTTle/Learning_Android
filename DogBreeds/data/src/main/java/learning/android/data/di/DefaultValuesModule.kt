package learning.android.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt Module to generate default values of primitive (like-of) which needs to be injected
 */
@Module
@InstallIn(SingletonComponent::class)
class DefaultValuesModule {

    /**
     * Generates default integer values
     */
    @Singleton
    @Provides
    fun provideDefaultInteger() = 0
}