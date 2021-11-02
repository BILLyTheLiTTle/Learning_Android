package learning.android.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DefaultValuesModule {

    @Singleton
    @Provides
    fun provideDefaultInteger() = 0
}