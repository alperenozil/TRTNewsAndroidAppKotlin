package com.news.assignment.rss.data.remote.newsresponse

import com.news.assignment.rss.data.local.NewsEntity

data class News(
    val categoryName: String? = null,
    val categorySlug: String? = null,
    val description: String? = null,
    val id: Int? = null,
    val mainImageUrl: String? = null,
    val path: String? = null,
    val title: String? = null,
    val isTranslated: Boolean = false
)

fun News.toNewsEntity(): NewsEntity =
    NewsEntity(
        id = id,
        newsTitle = title.toString(),
        newsDescription = description.toString(),
        mainImageUrl = mainImageUrl.toString(),
        path = path.toString(),
        isTranslated = true
    )
