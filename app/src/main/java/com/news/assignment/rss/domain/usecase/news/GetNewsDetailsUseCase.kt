package com.news.assignment.rss.domain.usecase.news

import com.news.assignment.rss.common.Resource
import com.news.assignment.rss.domain.repository.OpenAiRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNewsDetailsUseCase @Inject constructor(
    private val openAiRepository: OpenAiRepository
) {
    suspend fun summarize(urlPath: String): Flow<Resource<String>> = openAiRepository.summarizeText(urlPath)
}