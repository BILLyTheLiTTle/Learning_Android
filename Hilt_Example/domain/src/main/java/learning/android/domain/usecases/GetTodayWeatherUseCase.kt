package learning.android.domain.usecases

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.EntryPoint
import dagger.hilt.EntryPoints
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import learning.android.domain.models.request.City
import learning.android.domain.models.response.TodayWeatherModel
import learning.android.domain.repositories.RemoteRepo
import javax.inject.Inject

class GetTodayWeatherUseCase @Inject constructor(
    var city: City,
    private val apiRepo: RemoteRepo
    ): SingleUseCase<TodayWeatherModel> { //@JvmSuppressWildcards
    override fun execute() = apiRepo.getTodayWeather(city)



    /* This should be working, but it is not.
    TODO Figure out why this happening and make it work this way using @EntryPoint
    */
//    @EntryPoint
//    @InstallIn(SingletonComponent::class)
//    interface RemoteRepoEntryPoint {
//        fun getRemoteRepo(): RemoteRepo
//    }
//
//    private fun getRemoteRepo(@ApplicationContext appContext: Context): RemoteRepo {
//        val hiltRemoteRepoEntryPoint = EntryPoints.get(
//            appContext,
//            RemoteRepoEntryPoint::class.java
//        )
//        return hiltRemoteRepoEntryPoint.getRemoteRepo()
//    }
}