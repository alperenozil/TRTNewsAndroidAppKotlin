package com.news.assignment.rss.domain.usecase.chat

import app.cash.turbine.test
import com.news.assignment.rss.common.Resource
import com.news.assignment.rss.domain.repository.OpenAiRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class GetChatRecommendationsUseCaseTest {

    private lateinit var useCase: GetChatRecommendationsUseCase
    private val repository: OpenAiRepository = mock()

    @Before
    fun setup() {
        useCase = GetChatRecommendationsUseCase(repository)
    }

    @Test
    fun `getRecommendation returns expected Resource`() = runTest {
        val prompt = "Explain quantum physics"
        val news = listOf("News1", "News2")
        val expected = Resource.Success("Quantum physics is...")

        // Mock the repository response
        whenever(repository.getRecommendations(prompt, news)).thenReturn(flowOf(expected))

        // Collect from the use case's flow
        useCase.getRecommendation(prompt, news).test {
            val item = awaitItem()
            assertEquals(expected, item)
            awaitComplete()
        }
    }
}
