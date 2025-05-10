package com.news.assignment.rss.data.repository

import android.content.SharedPreferences
import com.news.assignment.rss.data.api.TranslationApi
import com.news.assignment.rss.data.remote.translation.TranslateRequest
import com.news.assignment.rss.domain.repository.TranslationRepository
import com.news.assignment.rss.domain.usecase.settings.LanguagePreferenceUseCase
import javax.inject.Inject

class TranslationRepositoryImpl @Inject constructor(
    private val translationApi: TranslationApi,
    private val languagePreferenceUseCase: LanguagePreferenceUseCase
) : TranslationRepository {
    override suspend fun getTranslation(text: String): String {
        val targetLanguage = languagePreferenceUseCase.getLanguage()
        return translationApi.translate(
            apiKey = "AIzaSyAJzePmCujVLTc3MzveKDNWm-laJsHSgIY",
            request = TranslateRequest(
                q = text,
                target = targetLanguage
            )
        ).data.translations.firstOrNull()?.translatedText ?: ""
    }
}