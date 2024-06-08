package com.timeless.kiels.data.mapper

import com.timeless.kiels.data.remote.ArticlesResponse
import com.timeless.kiels.data.remote.SourceDto
import com.timeless.kiels.data.local.article.ArticleEntity
import com.timeless.kiels.data.local.article.SourceEntity
import com.timeless.kiels.domain.model.Article
import com.timeless.kiels.domain.model.ArticlesBody
import com.timeless.kiels.domain.model.Source
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

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

        fun fromArticleEntityFlowListToArticleFlowList(articleEntityFlow : Flow<List<ArticleEntity>>) : Flow<List<Article>> {
            return articleEntityFlow.map {
                it.map {
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