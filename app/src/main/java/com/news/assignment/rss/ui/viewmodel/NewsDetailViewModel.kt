package com.news.assignment.rss.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.news.assignment.rss.common.Resource
import com.news.assignment.rss.domain.usecase.news.GetNewsDetailsUseCase
import com.news.assignment.rss.ui.state.UiDataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NewsDetailViewModel @Inject constructor(
    private val getNewsDetailsUseCase: GetNewsDetailsUseCase,
) : ViewModel() {

    private val _newsSummaryState = MutableStateFlow(value = UiDataState())
    val newsSummaryState = _newsSummaryState.asStateFlow()

    internal fun getSummary(urlPath: String) {
        viewModelScope.launch(Dispatchers.IO) {
            getNewsDetailsUseCase.summarize(urlPath = urlPath).collect{
                when (it) {
                    is Resource.Success -> _newsSummaryState.value = UiDataState(uiData = it.data)
                    is Resource.Loading -> _newsSummaryState.value = UiDataState(isLoading = true)
                    is Resource.Error -> _newsSummaryState.value = UiDataState(error = it.message)
                }
            }
        }
    }
}
