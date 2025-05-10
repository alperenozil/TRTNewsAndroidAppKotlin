package com.news.assignment.rss.domain.repository

import com.news.assignment.rss.common.Resource
import com.news.assignment.rss.data.local.NewsEntity
import com.news.assignment.rss.data.remote.newsresponse.News
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getNews(): Flow<Resource<List<News>>>
    suspend fun insertDatabaseEntry(entry: NewsEntity)
    suspend fun deleteAllNews()
}