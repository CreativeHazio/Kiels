package com.timeless.kiels.domain.usecases

import com.timeless.kiels.domain.model.Article
import com.timeless.kiels.domain.repository.ArticleRepository

class SaveStarredArticle(
    private val articleRepository: ArticleRepository
) {

    suspend operator fun invoke(article: Article) {
        articleRepository.saveArticleToRoom(article)
    }

}