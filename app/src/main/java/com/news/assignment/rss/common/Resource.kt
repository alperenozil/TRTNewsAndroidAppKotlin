package com.news.assignment.rss.common

sealed class Resource<ALP>(val data: ALP? = null, val message: String? = null) {
    class Success<ALP>(data: ALP) : Resource<ALP>(data)
    class Error<ALP>(message: String?, data: ALP? = null) : Resource<ALP>(data, message)
    class Loading<ALP>(data: ALP? = null) : Resource<ALP>(data)
}