package com.news.assignment.rss.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.news.assignment.rss.data.remote.newsresponse.News

@Entity(tableName = "news")
data class NewsEntity(
    @PrimaryKey val id: Int? = null,
    val newsTitle: String?,
    val newsDescription: String?,
    val isTranslated: Boolean = false,
    val mainImageUrl: String?,
    val path: String?,
)

fun NewsEntity.toNews(): News {
    return News(
        id = id,
        title = newsTitle,
        description = newsDescription,
        categoryName = "1",
        categorySlug = "1",
        mainImageUrl = mainImageUrl,
        path = path,
        isTranslated = true
    )
}