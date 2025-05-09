package com.news.assignment.rss.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.news.assignment.rss.common.Resource
import com.news.assignment.rss.domain.usecase.chat.GetChatRecommendationsUseCase
import com.news.assignment.rss.domain.usecase.news.GetNewsUseCase
import com.news.assignment.rss.ui.state.UiDataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val getChatRecommendationsUseCase: GetChatRecommendationsUseCase,
    private val getNewsUseCase: GetNewsUseCase,
) : ViewModel() {

    private val _newsRecommendationState = MutableStateFlow(value = UiDataState())
    val newsRecommendationState = _newsRecommendationState.asStateFlow()

    internal fun getRecommendation(prompt: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getNewsUseCase.getNews().collect{
                when (it) {
                    is Resource.Success -> {
                        getChatRecommendationsUseCase.getRecommendation(prompt = prompt, it.data!!.map { it.title!! })
                            .collect {
                                when (it) {
                                    is Resource.Success -> _newsRecommendationState.value =
                                        UiDataState(uiData = it.data)

                                    is Resource.Loading -> _newsRecommendationState.value =
                                        UiDataState(isLoading = true)

                                    is Resource.Error -> _newsRecommendationState.value =
                                        UiDataState(error = it.message)
                                }
                            }
                    }
                    is Resource.Loading -> {}
                    is Resource.Error -> {}
                }
            }

        }
    }
}
