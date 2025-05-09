package com.news.assignment.rss.data.remote.translation

data class TranslateRequest(
    val q: String,
    val target: String,
    val format: String = "text"
)

data class TranslateResponse(
    val data: TranslationData
)

data class TranslationData(
    val translations: List<Translation>
)

data class Translation(
    val translatedText: String
)