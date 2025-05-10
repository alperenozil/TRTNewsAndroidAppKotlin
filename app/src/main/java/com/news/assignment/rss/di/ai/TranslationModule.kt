package com.news.assignment.rss.di.ai

import android.content.Context
import android.content.SharedPreferences
import com.news.assignment.rss.common.Constants.URL_TRANSLATE
import com.news.assignment.rss.data.api.TranslationApi
import com.news.assignment.rss.data.repository.TranslationRepositoryImpl
import com.news.assignment.rss.domain.repository.TranslationRepository
import com.news.assignment.rss.domain.usecase.settings.LanguagePreferenceUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TranslationModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(
        @ApplicationContext context: Context
    ): SharedPreferences {
        return context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideLanguagePreferenceUseCase(
        sharedPreferences: SharedPreferences
    ) = LanguagePreferenceUseCase(sharedPreferences)


    @Provides
    @Singleton
    fun provideTranslationApi(): TranslationApi = Retrofit.Builder()
        .baseUrl(URL_TRANSLATE)
        .addConverterFactory(GsonConverterFactory.create())
        .client(OkHttpClient()).build().create(TranslationApi::class.java)

    @Provides
    @Singleton
    fun provideTranslationRepository(
        translationApi: TranslationApi,
        languagePreferenceUseCase: LanguagePreferenceUseCase
    ): TranslationRepository = TranslationRepositoryImpl(
        translationApi = translationApi,
        languagePreferenceUseCase = languagePreferenceUseCase
    )
}
