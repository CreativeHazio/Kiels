package com.timeless.kiels.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.timeless.kiels.data.remote.ArticlesAPI
import com.timeless.kiels.data.remote.ArticlesHeadlinePagingSource
import com.timeless.kiels.data.remote.LatestArticlesPagingSource
import com.timeless.kiels.data.local.article.ArticleDAO
import com.timeless.kiels.data.mapper.ArticlesMapper
import com.timeless.kiels.domain.model.Article
import com.timeless.kiels.domain.repository.ArticleRepository
import com.timeless.kiels.util.Constants
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ArticleRepositoryImpl @Inject constructor(
    private val articlesAPI : ArticlesAPI,
    private val articleDAO: ArticleDAO
) : ArticleRepository {

    override fun getArticlesHeadlinePagingData(keyword: String) : Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.ARTICLES_PAGE_SIZE
            ),
            pagingSourceFactory = { ArticlesHeadlinePagingSource(articlesAPI, keyword) }
        ).flow
    }

    override fun saveArticleToRoom(article: Article) {
        articleDAO.saveArticle(
            ArticlesMapper.fromArticleToArticleEntity(article)
        )
    }

    override fun deleteArticleFromRoom(articleTitle: String) {
        articleDAO.deleteArticle(articleTitle)
    }

    override suspend fun getArticlesFromRoom(): Flow<List<Article>> {
        return ArticlesMapper.fromArticleEntityFlowListToArticleFlowList(articleDAO.getArticles())
    }

    override fun getLatestArticlesPagingData(keyword: String) : Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.ARTICLES_PAGE_SIZE
            ),
            pagingSourceFactory = { LatestArticlesPagingSource(articlesAPI, keyword) }
        ).flow
    }
}