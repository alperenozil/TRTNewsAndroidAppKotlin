package com.news.assignment.rss.domain.usecase.settings

import android.content.SharedPreferences
import javax.inject.Inject

class LanguagePreferenceUseCase @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {
    fun setLanguage(languageCode: String) {
        sharedPreferences.edit().putString("target_language", languageCode).apply()
    }

    fun getLanguage(): String {
        return sharedPreferences.getString("target_language", "en") ?: "en"
    }
}
