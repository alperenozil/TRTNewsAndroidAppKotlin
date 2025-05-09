package com.news.assignment.rss.di.db

import android.content.Context
import androidx.room.Room
import com.news.assignment.rss.data.local.AppDatabase
import com.news.assignment.rss.data.local.NewsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideCityDao(appDatabase: AppDatabase): NewsDao {
        return appDatabase.newsDao()
    }
}