package com.news.assignment.rss.data.api

import com.news.assignment.rss.data.remote.newsresponse.NewsResponse
import com.news.assignment.rss.data.remote.openai.ChatRequest
import com.news.assignment.rss.data.remote.openai.ChatResponse
import com.news.assignment.rss.data.remote.translation.TranslateRequest
import com.news.assignment.rss.data.remote.translation.TranslateResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface FrenchApi{
    @GET("api/homepage")
    suspend fun getFrenchNews():NewsResponse
}

interface ArabicApi{
    @GET("api/homepage")
    suspend fun getArabicNews():NewsResponse
}

interface BalkanApi{
    @GET("api/homepage")
    suspend fun getBalkanNews():NewsResponse
}

interface AlbanianApi{
    @GET("api/homepage")
    suspend fun getAlbanianNews():NewsResponse
}

interface MacedonianApi{
    @GET("api/homepage")
    suspend fun getMacedonianNews():NewsResponse
}

interface RussianApi{
    @GET("api/homepage")
    suspend fun getRussianNews():NewsResponse
}

interface GermanApi{
    @GET("api/homepage")
    suspend fun getGermanNews():NewsResponse
}

interface TranslationApi {
    @POST("language/translate/v2")
    suspend fun translate(
        @Query("key") apiKey: String,
        @Body request: TranslateRequest
    ): TranslateResponse
}

interface OpenAiApi {
    @POST("v1/chat/completions")
    suspend fun getSummary(
        @Header("Authorization") authHeader: String,
        @Body request: ChatRequest
    ): ChatResponse
}