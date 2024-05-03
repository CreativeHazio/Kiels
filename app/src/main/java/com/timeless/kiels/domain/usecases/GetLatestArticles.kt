package com.timeless.kiels.domain.usecases

import androidx.paging.PagingData
import com.timeless.kiels.domain.model.Article
import com.timeless.kiels.domain.repository.ArticleRepository
import kotlinx.coroutines.flow.Flow

class GetLatestArticles(
    private val articleRepository: ArticleRepository
) {

    operator fun invoke(
        keyword: String
    ): Flow<PagingData<Article>> {
        return articleRepository.getLatestArticlesPagingData(keyword)
    }
}