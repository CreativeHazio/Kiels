package com.timeless.kiels.domain.repository

import androidx.paging.PagingData
import com.timeless.kiels.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface ArticleRepository {

    fun getLatestArticlesPagingData(keyword: String): Flow<PagingData<Article>>

    fun getArticlesHeadlinePagingData(keyword: String): Flow<PagingData<Article>>
}