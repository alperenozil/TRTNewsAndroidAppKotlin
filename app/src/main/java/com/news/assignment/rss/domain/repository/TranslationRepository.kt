package com.news.assignment.rss.domain.repository

interface TranslationRepository {
    suspend fun getTranslation(text: String): String
}

