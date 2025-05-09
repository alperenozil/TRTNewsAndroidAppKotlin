package com.news.assignment.rss.data.repository

import com.news.assignment.rss.common.Resource
import com.news.assignment.rss.data.api.OpenAiApi
import com.news.assignment.rss.data.remote.openai.ChatRequest
import com.news.assignment.rss.data.remote.openai.Message
import com.news.assignment.rss.domain.repository.OpenAiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class OpenAiRepositoryImpl @Inject constructor(
    private val openAiApi: OpenAiApi
) : OpenAiRepository {
    override suspend fun summarizeText(urlPath: String): Flow<Resource<String>> {
        val prompt = "Please summarize the following news url in a few concise sentences in english:\n$urlPath"
        val request = ChatRequest(
            messages = listOf(
                Message("system", "You are a helpful assistant that summarizes text."),
                Message("user", prompt)
            )
        )
        return flow {
            emit(Resource.Loading())
            try {
                emit(
                    Resource.Success(
                        openAiApi.getSummary(
                            "Bearer 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",
                            request
                        ).choices.firstOrNull()?.message?.content ?: "No summary found."
                    )
                )
            } catch (e: Exception) {
                emit(
                    Resource.Error("error while summarizing text")
                )
            }
        }
    }

    override suspend fun getRecommendations(prompt: String,news: List<String>): Flow<Resource<String>> {
        val request = ChatRequest(
            messages = listOf(
                Message("system", "You are a helpful assistant that recommends international news from these texts in english"),
                Message("user",prompt + news.toString())
            )
        )
        return flow {
            emit(Resource.Loading())
            try {
                emit(
                    Resource.Success(
                        openAiApi.getSummary(
                            "Bearer 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",
                            request
                        ).choices.firstOrNull()?.message?.content ?: "No recommendations found."
                    )
                )
            } catch (e: Exception) {
                emit(
                    Resource.Error("error while creating recommendations")
                )
            }
        }
    }
}