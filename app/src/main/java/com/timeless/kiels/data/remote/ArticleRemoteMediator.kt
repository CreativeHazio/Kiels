package com.timeless.kiels.data.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.timeless.kiels.data.local.article.ArticleDatabase
import com.timeless.kiels.data.local.article.ArticleEntity
import com.timeless.kiels.data.mapper.ArticlesMapper

@OptIn(ExperimentalPagingApi::class)
class ArticleRemoteMediator(
    private val searchQuery : String,
    private val articleDatabase: ArticleDatabase,
    private val articlesAPI: ArticlesAPI
) : RemoteMediator<Int, ArticleEntity>() {
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ArticleEntity>
    ): MediatorResult {
        return try {

            val loadKey  = when(loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        1
                    } else {
                        ((lastItem.id!!.div(state.config.pageSize)).plus(1))
                    }
                }
            }

            val articlesResponse = articlesAPI.getLatestArticlesFromNewsApi(
                searchQuery = searchQuery, pageNumber = loadKey, pageSize = state.config.pageSize
            )

            val articles = ArticlesMapper.fromArticlesResponseToArticleBodyEntity(
                articlesResponse.body()!!
            ).articles.distinctBy { it.title }.filterNot { it.title == "[Removed]" }

            articleDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    articleDatabase.articleDAO.clearAllArticles()
                }

                articleDatabase.articleDAO.saveAllArticles(articles)

            }

            MediatorResult.Success(endOfPaginationReached = articles.isEmpty())

        } catch (e : Exception) {
            MediatorResult.Error(e)
        }
    }
}