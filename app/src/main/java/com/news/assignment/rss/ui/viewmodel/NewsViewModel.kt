package com.news.assignment.rss.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.news.assignment.rss.common.Resource
import com.news.assignment.rss.data.remote.newsresponse.News
import com.news.assignment.rss.data.remote.newsresponse.toNewsEntity
import com.news.assignment.rss.domain.usecase.news.GetNewsUseCase
import com.news.assignment.rss.ui.state.NewsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase,
) : ViewModel() {

    private val _newsState = MutableStateFlow(value = NewsState())
    val newsState = _newsState.asStateFlow()

    init {
        getNews()
    }

    private fun getNews() {
        viewModelScope.launch(Dispatchers.IO) {
            val news = getNewsUseCase.getNews()
            news.collect {
                when (it) {
                    is Resource.Success -> translate(it.data)
                    is Resource.Loading -> _newsState.value = NewsState(isLoading = true)
                    is Resource.Error -> _newsState.value = NewsState(error = "Error")
                }
            }
        }
    }

    private fun translate(data: List<News>?) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                data?.let { newsList ->
                    val updatedNewsList = newsList.filter { !it.isTranslated }.map {
                        it.copy(
                            title = getNewsUseCase.getTranslation(it.title!!),
                            description = getNewsUseCase.getTranslation(it.description!!)
                        )
                    }
                    if (updatedNewsList.isNotEmpty()) {
                        _newsState.value = NewsState(items = updatedNewsList)
                    } else _newsState.value = NewsState(items = data)
                    updatedNewsList.forEach { getNewsUseCase.insertNews(it.toNewsEntity()) }
                }
            } catch (e: Exception) {
                _newsState.value = NewsState(items = data)
                println("Error in translate function: ${e.message}")
            }
        }
    }
}
