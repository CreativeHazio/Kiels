package com.timeless.kiels.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface ArticleDAO {

    @Upsert
    fun saveArticle(articleEntity: ArticleEntity)

    @Query("DELETE FROM articles_table WHERE title = :articleTitle")
    fun deleteArticle(articleTitle : String)

    @Query("SELECT * FROM articles_table")
    fun getArticles() : List<ArticleEntity>

}