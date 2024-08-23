package com.timeless.kiels.domain.repository

import androidx.paging.PagingData
import com.timeless.kiels.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface ArticleRepository {

    fun getLatestArticlesPagingData(searchQuery: String): Flow<PagingData<Article>>

    suspend fun saveStarredArticleToRoom(article: Article)

    suspend fun deleteStarredArticleFromRoom(article: Article)

    suspend fun getStarredArticlesFromRoom() : Flow<List<Article>>

    fun exploreArticles(searchQuery: String): Flow<PagingData<Article>>
}