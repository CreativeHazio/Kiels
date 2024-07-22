package com.timeless.kiels.domain.repository

import androidx.paging.PagingData
import com.timeless.kiels.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface ArticleRepository {

    fun getLatestArticlesPagingData(searchQuery: String): Flow<PagingData<Article>>

    suspend fun saveArticleToRoom(article: Article)

    suspend fun deleteArticleFromRoom(article: Article)

    suspend fun getArticlesFromRoom() : Flow<List<Article>>
}