package learning.android.navigation.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import learning.android.navigation.Navigator
import learning.android.navigation.NavigatorImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NavigationModule {
    @Singleton
    @Provides
    fun provideNavigator(): Navigator = NavigatorImpl()
}