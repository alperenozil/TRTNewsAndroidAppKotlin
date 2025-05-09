package com.news.assignment.rss.ui.state

import com.news.assignment.rss.data.remote.newsresponse.News

class NewsState(
    val isLoading: Boolean = false,
    val items: List<News>? = null,
    val error: String? = ""
)