package com.timeless.kiels.domain.usecases

import com.timeless.kiels.data.local.ArticleDAO
import com.timeless.kiels.domain.mapper.ArticlesMapper
import com.timeless.kiels.domain.model.Article

class SaveStarredArticle(
    private val articleDAO: ArticleDAO
) {

    operator fun invoke(article: Article) {
        articleDAO.saveArticle(
            ArticlesMapper.fromArticleToArticleEntity(article)
        )
    }

}