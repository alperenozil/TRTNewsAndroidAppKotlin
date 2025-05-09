package com.news.assignment.rss.domain.usecase.chat

import com.news.assignment.rss.common.Resource
import com.news.assignment.rss.domain.repository.OpenAiRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetChatRecommendationsUseCase @Inject constructor(
    private val openAiRepository: OpenAiRepository
) {
    suspend fun getRecommendation(prompt: String, news: List<String>): Flow<Resource<String>> = openAiRepository.getRecommendations(prompt,news)
}