package com.news.assignment.rss.domain.usecase.news

import com.news.assignment.rss.common.Resource
import com.news.assignment.rss.data.local.NewsEntity
import com.news.assignment.rss.data.remote.newsresponse.News
import com.news.assignment.rss.domain.repository.NewsRepository
import com.news.assignment.rss.domain.repository.TranslationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNewsWithTranslationsUseCase @Inject constructor(
    private val newsRepository: NewsRepository,
    private val translationRepository: TranslationRepository,
) {
    suspend fun getNews(): Flow<Resource<List<News>>> = newsRepository.getNews()
    suspend fun getTranslation(text: String): String = translationRepository.getTranslation(text)
    suspend fun insertNews(entry: NewsEntity) = newsRepository.insertDatabaseEntry(entry)
    suspend fun deleteAllNews() = newsRepository.deleteAllNews()
}