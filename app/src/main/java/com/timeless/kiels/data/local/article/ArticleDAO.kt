package com.timeless.kiels.data.local.article

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDAO {

    @Upsert
    fun saveArticle(articleEntity: ArticleEntity)

    @Query("DELETE FROM articles_table WHERE title = :articleTitle")
    fun deleteArticle(articleTitle : String)

    @Query("SELECT * FROM articles_table")
    fun getArticles() : Flow<List<ArticleEntity>>

}