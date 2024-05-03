package com.timeless.kiels.domain.mapper

import com.timeless.kiels.data.api.ArticlesResponse
import com.timeless.kiels.data.api.SourceDto
import com.timeless.kiels.data.local.ArticleEntity
import com.timeless.kiels.data.local.SourceEntity
import com.timeless.kiels.domain.model.Article
import com.timeless.kiels.domain.model.ArticlesBody
import com.timeless.kiels.domain.model.Source

class ArticlesMapper {

    companion object {

        fun fromArticlesResponseToArticlesBody(articlesResponse: ArticlesResponse) : ArticlesBody {
            return ArticlesBody(
                articles = articlesResponse.articles.map {
                    Article(
                        it.author,
                        it.content,
                        it.description,
                        it.publishedAt,
                        fromSourceDtoToSource(it.source),
                        it.title,
                        it.url,
                        it.urlToImage
                    )
                } as MutableList<Article>,
                status = articlesResponse.status,
                totalResults = articlesResponse.totalResults
            )
        }

        private fun fromSourceDtoToSource(sourceDto: SourceDto) : Source {
            return Source(
                sourceDto.id,
                sourceDto.name
            )
        }

        fun fromArticleEntityListToArticleList(articleEntity : List<ArticleEntity>) : List<Article> {
            return articleEntity.map {
                Article(
                    it.author,
                    it.content,
                    it.description,
                    it.publishedAt,
                    fromSourceEntityToSource(it.source),
                    it.title,
                    it.url,
                    it.urlToImage
                )
            }

        }

        private fun fromSourceEntityToSource(sourceEntity: SourceEntity) : Source {
            return Source(
                sourceEntity.id,
                sourceEntity.name
            )
        }

        fun fromArticleToArticleEntity(article: Article) : ArticleEntity {
            return ArticleEntity(
                article.title!!,
                article.author!!,
                article.content!!,
                article.description!!,
                article.publishedAt!!,
                fromSourceToSourceEntity(article.source),
                article.url!!,
                article.urlToImage!!
            )
        }

        private fun fromSourceToSourceEntity(source: Source?) : SourceEntity {
            return SourceEntity(
                source?.id ?: "",
                source?.name ?: ""
            )
        }

    }

}