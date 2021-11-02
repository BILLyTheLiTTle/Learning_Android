package learning.android.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import learning.android.data.repo.RemoteRepoImpl
import learning.android.domain.repositories.RemoteRepo
import javax.inject.Singleton

/**
 * Hilt Module to generate repository related module
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteRepoModule {

    @Singleton
    @Binds
    abstract fun bindRemoteRepo(remoteRepoImpl: RemoteRepoImpl): RemoteRepo
}