package io.piestack.movies.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import io.piestack.movies.database.AppDatabase
import io.piestack.movies.database.MovieDb
import io.piestack.movies.database.MovieDbClient
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, "movies-test").fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideUserDb(appDatabase: AppDatabase): MovieDb {
        return MovieDbClient(appDatabase.movieDao)
    }
}