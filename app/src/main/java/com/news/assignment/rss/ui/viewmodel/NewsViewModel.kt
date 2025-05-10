package com.news.assignment.rss.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.news.assignment.rss.common.Resource
import com.news.assignment.rss.data.remote.newsresponse.News
import com.news.assignment.rss.data.remote.newsresponse.toNewsEntity
import com.news.assignment.rss.domain.usecase.news.GetNewsWithTranslationsUseCase
import com.news.assignment.rss.ui.state.NewsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getNewsWithTranslationsUseCase: GetNewsWithTranslationsUseCase,
) : ViewModel() {

    private val _newsState = MutableStateFlow(value = NewsState())
    val newsState = _newsState.asStateFlow()

    init {
        getNews()
    }

    private fun getNews() {
        viewModelScope.launch(Dispatchers.IO) {
            val news = getNewsWithTranslationsUseCase.getNews()
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
                data?.forEach { item ->
                    val translatedItem = if (!item.isTranslated) {
                        item.copy(
                            title = getNewsWithTranslationsUseCase.getTranslation(item.title!!),
                            description = getNewsWithTranslationsUseCase.getTranslation(item.description!!),
                            isTranslated = true
                        )
                    } else item

                    // Add the translated item to the state one by one
                    val currentList = _newsState.value.items ?: emptyList()
                    _newsState.value = NewsState(
                        items = currentList + translatedItem,
                        isLoading = false
                    )

                    // Insert into DB
                    getNewsWithTranslationsUseCase.insertNews(translatedItem.toNewsEntity())
                }
            } catch (e: Exception) {
                _newsState.value = NewsState(
                    items = data,
                    isLoading = false
                )
                println("Error in translate function: ${e.message}")
            }
        }
    }

}
