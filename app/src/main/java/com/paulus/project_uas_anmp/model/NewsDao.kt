package com.paulus.project_uas_anmp.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg news:News)

    @Query("SELECT * FROM news")
    fun selectAllNews(): List<News>

    @Query("SELECT * FROM news WHERE uuid= :id")
    fun selectNews(id:Int): News

    @Delete
    fun deleteNews(news:News)
}