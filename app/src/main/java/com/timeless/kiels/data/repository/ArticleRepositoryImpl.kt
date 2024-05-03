package com.timeless.kiels.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.timeless.kiels.data.api.ArticlesAPI
import com.timeless.kiels.data.api.ArticlesHeadlinePagingSource
import com.timeless.kiels.data.api.LatestArticlesPagingSource
import com.timeless.kiels.domain.model.Article
import com.timeless.kiels.domain.repository.ArticleRepository
import com.timeless.kiels.util.Constants
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ArticleRepositoryImpl @Inject constructor(
    private val articlesAPI : ArticlesAPI
) : ArticleRepository {

    override fun getArticlesHeadlinePagingData(keyword: String) : Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(
                pageSize = Constants.ARTICLES_PAGE_SIZE
            ),
            pagingSourceFactory = { ArticlesHeadlinePagingSource(articlesAPI, keyword) }
        ).flow
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