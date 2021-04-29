package learning.android.data.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import learning.android.data.apiservice.ApiService
import learning.android.data.mappers.WeatherMapper
import learning.android.data.repo.RemoteRepoImpl
import learning.android.domain.repositories.RemoteRepo
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteRepoModule {

    @Singleton
    @Binds
    abstract fun bindRemoteRepo(remoteRepoImpl: RemoteRepoImpl): RemoteRepo
}

/*
In case you don't like to use the @Binds and you prefer @Provides then comment the code
above and use the below one.
Pay attention, that the parameters of provideXXX() should be the same as the constructor of the object it creates.
 */

//@Module
//@InstallIn(SingletonComponent::class)
//class RemoteRepoModule {
//
//    @Singleton
//    @Provides
//    fun provideRemoteRepo(apiService: ApiService, weatherMapper: dagger.Lazy<WeatherMapper>): RemoteRepo {
//        return RemoteRepoImpl(apiService, weatherMapper)
//    }
//}