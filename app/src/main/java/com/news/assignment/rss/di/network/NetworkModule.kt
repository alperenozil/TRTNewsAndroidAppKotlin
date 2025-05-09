package com.news.assignment.rss.di.network

import com.news.assignment.rss.common.Constants.URL_ALBANIAN
import com.news.assignment.rss.common.Constants.URL_ARABIC
import com.news.assignment.rss.common.Constants.URL_BALKAN
import com.news.assignment.rss.common.Constants.URL_FRENCH
import com.news.assignment.rss.common.Constants.URL_GERMAN
import com.news.assignment.rss.common.Constants.URL_MACEDONIAN
import com.news.assignment.rss.common.Constants.URL_RUSSIAN
import com.news.assignment.rss.data.api.AlbanianApi
import com.news.assignment.rss.data.api.ArabicApi
import com.news.assignment.rss.data.api.BalkanApi
import com.news.assignment.rss.data.api.FrenchApi
import com.news.assignment.rss.data.api.GermanApi
import com.news.assignment.rss.data.api.MacedonianApi
import com.news.assignment.rss.data.api.RussianApi
import com.news.assignment.rss.data.local.NewsDao
import com.news.assignment.rss.data.repository.NewsRepositoryImpl
import com.news.assignment.rss.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideFrenchApi(): FrenchApi =
        Retrofit.Builder().baseUrl(URL_FRENCH).addConverterFactory(
            GsonConverterFactory.create()
        ).client(OkHttpClient()).build().create(FrenchApi::class.java)


    @Provides
    @Singleton
    fun provideArabicApi(): ArabicApi =
        Retrofit.Builder().baseUrl(URL_ARABIC).addConverterFactory(
            GsonConverterFactory.create()
        ).client(OkHttpClient()).build().create(ArabicApi::class.java)

    @Provides
    @Singleton
    fun provideBalkanApi(): BalkanApi =
        Retrofit.Builder().baseUrl(URL_BALKAN).addConverterFactory(
            GsonConverterFactory.create()
        ).client(OkHttpClient()).build().create(BalkanApi::class.java)

    @Provides
    @Singleton
    fun provideAlbanianApi(): AlbanianApi =
        Retrofit.Builder().baseUrl(URL_ALBANIAN).addConverterFactory(
            GsonConverterFactory.create()
        ).client(OkHttpClient()).build().create(AlbanianApi::class.java)

    @Provides
    @Singleton
    fun provideMacedonianApi(): MacedonianApi =
        Retrofit.Builder().baseUrl(URL_MACEDONIAN).addConverterFactory(
            GsonConverterFactory.create()
        ).client(OkHttpClient()).build().create(MacedonianApi::class.java)

    @Provides
    @Singleton
    fun provideRussianApi(): RussianApi =
        Retrofit.Builder().baseUrl(URL_RUSSIAN).addConverterFactory(
            GsonConverterFactory.create()
        ).client(OkHttpClient()).build().create(RussianApi::class.java)

    @Provides
    @Singleton
    fun provideGermanApi(): GermanApi =
        Retrofit.Builder().baseUrl(URL_GERMAN).addConverterFactory(
            GsonConverterFactory.create()
        ).client(OkHttpClient()).build().create(GermanApi::class.java)

    @Provides
    @Singleton
    fun provideNewsRepository(
        frenchApi: FrenchApi,
        arabicApi: ArabicApi,
        balkanApi: BalkanApi,
        albanianApi: AlbanianApi,
        macedonianApi: MacedonianApi,
        russianApi: RussianApi,
        germanApi: GermanApi,
        dao: NewsDao,
    ): NewsRepository = NewsRepositoryImpl(
        frenchApi = frenchApi,
        arabicApi = arabicApi,
        balkanApi = balkanApi,
        albanianApi = albanianApi,
        macedonianApi = macedonianApi,
        russianApi = russianApi,
        germanApi = germanApi,
        dao = dao
    )
}