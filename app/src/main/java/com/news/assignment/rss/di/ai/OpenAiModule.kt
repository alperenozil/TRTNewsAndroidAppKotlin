package com.news.assignment.rss.di.ai

import com.news.assignment.rss.common.Constants.URL_OPEN_AI
import com.news.assignment.rss.data.api.OpenAiApi
import com.news.assignment.rss.data.repository.OpenAiRepositoryImpl
import com.news.assignment.rss.domain.repository.OpenAiRepository
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
object OpenAiModule {
    @Provides
    @Singleton
    fun provideOpenAiApi(): OpenAiApi = Retrofit.Builder()
        .baseUrl(URL_OPEN_AI)
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient()).build().create(OpenAiApi::class.java)

    @Provides
    @Singleton
    fun provideOpenAiRepository(
        openAiApi: OpenAiApi
    ): OpenAiRepository = OpenAiRepositoryImpl(
        openAiApi = openAiApi
    )
}