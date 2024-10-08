package com.timeless.kiels.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.room.withTransaction
import com.timeless.kiels.core.util.Constants
import com.timeless.kiels.data.local.article.ArticleDatabase
import com.timeless.kiels.data.mapper.ArticlesMapper
import com.timeless.kiels.data.remote.ArticleRemoteMediator
import com.timeless.kiels.data.remote.ArticlesAPI
import com.timeless.kiels.data.remote.ExploreArticlesPagingSource
import com.timeless.kiels.domain.model.Article
import com.timeless.kiels.domain.repository.ArticleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class ArticleRepositoryImpl @Inject constructor(
    private val articlesAPI : ArticlesAPI,
    private val articleDatabase: ArticleDatabase
) : ArticleRepository {

    override suspend fun saveStarredArticleToRoom(article: Article) {
        articleDatabase.withTransaction {
            articleDatabase.articleDAO.starArticle(
                ArticlesMapper.fromArticleToStarredArticleEntity(article)
            )
            articleDatabase.articleDAO.updateArticle(
                ArticlesMapper.fromArticleToArticleEntity(article)
            )
        }
    }

    override suspend fun deleteStarredArticleFromRoom(article: Article) {
        articleDatabase.withTransaction {
            articleDatabase.articleDAO.deleteArticle(
                ArticlesMapper.fromArticleToStarredArticleEntity(article)
            )
            articleDatabase.articleDAO.updateArticle(
                ArticlesMapper.fromArticleToArticleEntity(article)
            )
        }

    }

    override suspend fun getStarredArticlesFromRoom(): Flow<List<Article>> {
        return ArticlesMapper.fromStarredArticleEntityFlowListToArticleFlowList(
            articleDatabase.articleDAO.getArticles().map { starredArticleEntityList ->
                starredArticleEntityList.sortedByDescending { it.dateAdded }
            }
        )
    }

    override fun getLatestArticlesPagingData(searchQuery: String) : Flow<PagingData<Article>> {
        return ArticlesMapper.fromArticleEntityFlowPagingDataToArticleFlowPagingData(
            Pager(
                config = PagingConfig(
                    pageSize = Constants.ARTICLES_PAGE_SIZE
                ),
                remoteMediator = ArticleRemoteMediator(searchQuery, articleDatabase, articlesAPI),
                pagingSourceFactory = { articleDatabase.articleDAO.pagingSource() }
            ).flow
        )
    }

    override fun exploreArticles(searchQuery: String) : Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.ARTICLES_PAGE_SIZE
            ),
            pagingSourceFactory = { ExploreArticlesPagingSource(articlesAPI, searchQuery) }
        ).flow
    }
}