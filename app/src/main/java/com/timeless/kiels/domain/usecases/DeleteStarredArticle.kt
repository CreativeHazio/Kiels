package com.timeless.kiels.domain.usecases

import com.timeless.kiels.data.local.ArticleDAO
import com.timeless.kiels.domain.mapper.ArticlesMapper
import com.timeless.kiels.domain.model.Article
import com.timeless.kiels.domain.repository.ArticleRepository

class DeleteStarredArticle(
    private val articleRepository: ArticleRepository
) {

    operator fun invoke(article: Article) {
        articleRepository.deleteArticleFromRoom(article.title!!)
    }

}