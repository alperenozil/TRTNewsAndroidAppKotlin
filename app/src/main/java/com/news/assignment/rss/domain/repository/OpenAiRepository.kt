package com.news.assignment.rss.domain.repository

import com.news.assignment.rss.common.Resource
import kotlinx.coroutines.flow.Flow

interface OpenAiRepository {
    suspend fun summarizeText(urlPath: String): Flow<Resource<String>>
    suspend fun getRecommendations(prompt: String,news: List<String>): Flow<Resource<String>>
}