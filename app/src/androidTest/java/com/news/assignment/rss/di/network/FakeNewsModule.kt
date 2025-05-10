package com.news.assignment.rss.di.network

import com.news.assignment.rss.common.Resource
import com.news.assignment.rss.data.api.AlbanianApi
import com.news.assignment.rss.data.api.ArabicApi
import com.news.assignment.rss.data.api.BalkanApi
import com.news.assignment.rss.data.api.FrenchApi
import com.news.assignment.rss.data.api.GermanApi
import com.news.assignment.rss.data.api.MacedonianApi
import com.news.assignment.rss.data.api.RussianApi
import com.news.assignment.rss.data.local.NewsDao
import com.news.assignment.rss.data.local.NewsEntity
import com.news.assignment.rss.data.remote.newsresponse.News
import com.news.assignment.rss.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import io.mockk.mockk
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [NetworkModule::class]
)
object FakeNewsModule {
    @Provides
    @Singleton
    fun provideFakeNewsRepository(): NewsRepository {
        return object : NewsRepository {
            override suspend fun getNews(): Flow<Resource<List<News>>> {
                return flowOf(
                    Resource.Success(
                        listOf(
                            News("Fake Title", "Fake Description", isTranslated = true)
                        )
                    )
                )
            }

            override suspend fun insertDatabaseEntry(entry: NewsEntity) {}
            override suspend fun deleteAllNews() {}
        }
    }
    @Provides fun provideFrenchApi(): FrenchApi = mockk()
    @Provides fun provideArabicApi(): ArabicApi = mockk()
    @Provides fun provideBalkanApi(): BalkanApi = mockk()
    @Provides fun provideAlbanianApi(): AlbanianApi = mockk()
    @Provides fun provideMacedonianApi(): MacedonianApi = mockk()
    @Provides fun provideRussianApi(): RussianApi = mockk()
    @Provides fun provideGermanApi(): GermanApi = mockk()
    @Provides fun provideNewsDao(): NewsDao = mockk()
}
