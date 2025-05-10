package com.news.assignment.rss.ui.viewmodel.settings

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.news.assignment.rss.domain.usecase.news.GetNewsWithTranslationsUseCase
import com.news.assignment.rss.domain.usecase.settings.LanguagePreferenceUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LanguageViewModel @Inject constructor(
    private val languagePreferenceUseCase: LanguagePreferenceUseCase,
    private val getNewsWithTranslationsUseCase: GetNewsWithTranslationsUseCase
) : ViewModel() {

    private val _selectedLanguage = mutableStateOf(languagePreferenceUseCase.getLanguage())
    val selectedLanguage: State<String> = _selectedLanguage

    fun onLanguageSelected(languageCode: String) {
        _selectedLanguage.value = languageCode
        languagePreferenceUseCase.setLanguage(languageCode)
        viewModelScope.launch(Dispatchers.IO) {
            getNewsWithTranslationsUseCase.deleteAllNews()
        }
    }
}
