package com.timeless.kiels.data.mapper

import androidx.paging.PagingData
import androidx.paging.map
import com.timeless.kiels.data.local.article.ArticleBodyEntity
import com.timeless.kiels.data.local.article.ArticleEntity
import com.timeless.kiels.data.local.article.SourceEntity
import com.timeless.kiels.data.local.article.StarredArticleEntity
import com.timeless.kiels.data.local.article.StarredSourceEntity
import com.timeless.kiels.data.remote.ArticlesResponse
import com.timeless.kiels.data.remote.SourceDto
import com.timeless.kiels.domain.model.Article
import com.timeless.kiels.domain.model.Source
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ArticlesMapper {

    companion object {

        fun fromArticlesResponseToArticleBodyEntity(articlesResponse: ArticlesResponse) : ArticleBodyEntity {
            return ArticleBodyEntity(
                articles = articlesResponse.articles.map {
                    ArticleEntity(
                        null,
                        it.title ?: "",
                        it.author ?: "",
                        it.content ?: "",
                        it.description ?: "",
                        it.publishedAt ?: "",
                        fromSourceDtoToSourceEntity(it.source),
                        it.url ?: "",
                        it.urlToImage ?: ""
                    )
                } as MutableList<ArticleEntity>,
                status = articlesResponse.status,
                totalResults = articlesResponse.totalResults
            )
        }

        private fun fromSourceDtoToSourceEntity(sourceDto: SourceDto?) : SourceEntity {
            return SourceEntity(
                sourceDto?.id ?: "",
                sourceDto?.name ?: ""
            )
        }

        // Change to starArticleEntity
        fun fromStarredArticleEntityFlowListToArticleFlowList(
            articleEntityFlow : Flow<List<StarredArticleEntity>>
        ) : Flow<List<Article>> {
            return articleEntityFlow.map {
                it.map {
                    Article(
                        it.id,
                        it.title,
                        it.author,
                        it.content,
                        it.description,
                        it.publishedAt,
                        fromStarredSourceEntityToSource(it.source),
                        it.url,
                        it.urlToImage,
                        it.isStarred
                    )
                }
            }

        }

        private fun fromStarredSourceEntityToSource(starredSourceEntity: StarredSourceEntity) : Source {
            return Source(
                starredSourceEntity.id,
                starredSourceEntity.name
            )
        }

        fun fromArticleEntityFlowPagingDataToArticleFlowPagingData(
            articleEntityFlowPagingData : Flow<PagingData<ArticleEntity>>
        ) : Flow<PagingData<Article>> {
            return articleEntityFlowPagingData.map {
                it.map {
                    Article(
                        it.id,
                        it.title,
                        it.author,
                        it.content,
                        it.description,
                        it.publishedAt,
                        fromSourceEntityToSource(it.source),
                        it.url,
                        it.urlToImage,
                        it.isStarred
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

        // Change to StarArticleEntity
        fun fromArticleToStarredArticleEntity(article: Article) : StarredArticleEntity {
            return StarredArticleEntity(
                article.id!!,
                article.title!!,
                article.author!!,
                article.content!!,
                article.description!!,
                article.publishedAt!!,
                fromSourceToStarredSourceEntity(article.source),
                article.url!!,
                article.urlToImage!!,
                article.isStarred
            )
        }

        private fun fromSourceToStarredSourceEntity(source: Source?) : StarredSourceEntity {
            return StarredSourceEntity(
                source?.id ?: "",
                source?.name ?: ""
            )
        }

        fun fromArticleToArticleEntity(article: Article) : ArticleEntity {
            return ArticleEntity(
                article.id!!,
                article.title!!,
                article.author!!,
                article.content!!,
                article.description!!,
                article.publishedAt!!,
                fromSourceToSourceEntity(article.source),
                article.url!!,
                article.urlToImage!!,
                article.isStarred
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