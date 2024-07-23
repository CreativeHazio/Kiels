package com.timeless.kiels.data.local.article

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface ArticleDAO {

    @Upsert
    fun starArticle(starredArticleEntity: StarredArticleEntity) // starred article

    @Upsert
    fun updateArticle(articleEntity: ArticleEntity)

    @Upsert
    fun saveAllArticles(articleEntityList : List<ArticleEntity>)

    @Delete
    fun deleteArticle(starredArticleEntity: StarredArticleEntity) // starred article

    @Query("SELECT * FROM starred_articles_table")
    fun getArticles() : Flow<List<StarredArticleEntity>> // starred article table

    @Query("SELECT * FROM articles_table")
    fun pagingSource() : PagingSource<Int, ArticleEntity>

    @Query("DELETE FROM articles_table")
    fun clearAllArticles()

}