package com.news.assignment.rss.data.repository

import com.news.assignment.rss.common.Resource
import com.news.assignment.rss.data.api.AlbanianApi
import com.news.assignment.rss.data.api.ArabicApi
import com.news.assignment.rss.data.api.BalkanApi
import com.news.assignment.rss.data.api.FrenchApi
import com.news.assignment.rss.data.api.GermanApi
import com.news.assignment.rss.data.api.MacedonianApi
import com.news.assignment.rss.data.api.RussianApi
import com.news.assignment.rss.data.local.NewsDao
import com.news.assignment.rss.data.local.NewsEntity
import com.news.assignment.rss.data.local.toNews
import com.news.assignment.rss.data.remote.newsresponse.News
import com.news.assignment.rss.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val frenchApi: FrenchApi,
    private val arabicApi: ArabicApi,
    private val balkanApi: BalkanApi,
    private val albanianApi: AlbanianApi,
    private val macedonianApi: MacedonianApi,
    private val russianApi: RussianApi,
    private val germanApi: GermanApi,
    private val dao: NewsDao
) : NewsRepository {
    override suspend fun getNews(): Flow<Resource<List<News>>> =
        flow {
            emit(Resource.Loading())
            if (dao.getNews().isNotEmpty()) {
                try {
                    val newsFromDb = dao.getNews()
                    println(newsFromDb)
                    emit(Resource.Success(dao.getNews().map { it.toNews() }))
                } catch (e: Exception) {
                    emit(
                        Resource.Error(
                            "error while loading data from database"
                        )
                    )
                }
            } else {
                try {
                    val aggredatedNews =
                        arabicApi.getArabicNews().news.map { it.copy(path = "https://www.trtarabi.com" + it.path) } +
                                balkanApi.getBalkanNews().news.map { it.copy(path = "https://bhsc.trtbalkan.com" + it.path) } +
                                albanianApi.getAlbanianNews().news.map { it.copy(path = "https://albanian.trtbalkan.com" + it.path) } +
                                frenchApi.getFrenchNews().news.map { it.copy(path = "https://www.trtfrancais.com" + it.path) } +
                                russianApi.getRussianNews().news.map { it.copy(path = "https://www.trtrussian.com" + it.path) } +
                                germanApi.getGermanNews().news.map { it.copy(path = "https://www.trtdeutsch.com" + it.path) } +
                                macedonianApi.getMacedonianNews().news.map { it.copy(path = "https://macedonian.trtbalkan.com" + it.path) }
                    emit(
                        Resource.Success(aggredatedNews)
                    )
                } catch (e: Exception) {
                    emit(
                        Resource.Error(
                            "error while fetching news"
                        )
                    )
                }
            }


        }

    override suspend fun insertDatabaseEntry(entry: NewsEntity) {
        dao.insert(entry)
    }
}