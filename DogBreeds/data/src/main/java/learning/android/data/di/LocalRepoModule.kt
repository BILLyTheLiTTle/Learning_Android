package learning.android.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import learning.android.data.api.local.db.AppDatabase
import learning.android.data.api.local.db.BreedDao
import learning.android.data.mappers.BreedMapper
import learning.android.data.repo.LocalRepoImpl
import learning.android.domain.repositories.LocalRepo
import javax.inject.Singleton

/**
 * Hilt Module to generate local repository related module
 */
@Module
@InstallIn(SingletonComponent::class)
class LocalRepoModule {

    @Singleton
    @Provides
    fun provideBreedDao(appDb: AppDatabase): BreedDao =
        appDb.breedDao()

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "Breeds"
        ).build()
    }

    @Singleton
    @Provides
    fun provideLocalRepo(breedDao: BreedDao,breedMapper: dagger.Lazy<BreedMapper>): LocalRepo =
        LocalRepoImpl(breedDao, breedMapper)
}