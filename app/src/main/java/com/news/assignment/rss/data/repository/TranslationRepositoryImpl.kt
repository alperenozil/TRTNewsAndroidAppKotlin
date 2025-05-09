package com.news.assignment.rss.data.repository

import com.news.assignment.rss.data.api.TranslationApi
import com.news.assignment.rss.data.remote.translation.TranslateRequest
import com.news.assignment.rss.domain.repository.TranslationRepository
import javax.inject.Inject

class TranslationRepositoryImpl @Inject constructor(
    private val translationApi: TranslationApi
) : TranslationRepository {
    override suspend fun getTranslation(text: String): String {
        return translationApi.translate(
            apiKey = "AIzaSyAJzePmCujVLTc3MzveKDNWm-laJsHSgIY",
            request = TranslateRequest(
                q = text,
                target = "en"
            )
        ).data.translations.firstOrNull()?.translatedText ?: ""
    }
}