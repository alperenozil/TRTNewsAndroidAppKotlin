package com.news.assignment.rss.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(newsEntity: NewsEntity)

    @Query("SELECT * FROM news")
    fun getNews(): List<NewsEntity>

    @Query("DELETE FROM news")
    fun deleteAllNews()
}