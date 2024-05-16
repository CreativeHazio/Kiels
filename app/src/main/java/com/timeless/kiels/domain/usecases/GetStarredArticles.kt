package com.timeless.kiels.domain.usecases

import com.timeless.kiels.data.local.ArticleDAO
import com.timeless.kiels.domain.mapper.ArticlesMapper
import com.timeless.kiels.domain.model.Article
import com.timeless.kiels.domain.repository.ArticleRepository
import kotlinx.coroutines.flow.Flow

class GetStarredArticles(
    private val articleRepository: ArticleRepository
) {

    suspend operator fun invoke() : Flow<List<Article>> {
        return articleRepository.getArticlesFromRoom()
    }

}