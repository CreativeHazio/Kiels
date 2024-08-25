package com.timeless.kiels.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.timeless.kiels.data.mapper.ArticlesMapper
import com.timeless.kiels.domain.model.Article

class ExploreArticlesPagingSource(
    private val articlesAPI: ArticlesAPI,
    private val searchQuery : String
) : PagingSource<Int, Article>() {
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val pageNumber = params.key ?: 1

            val articlesResponseBody = ArticlesMapper.fromArticlesResponseToArticleBody(
                articlesAPI.getLatestArticlesFromNewsApi(
                    searchQuery = searchQuery, pageNumber = pageNumber, pageSize = params.loadSize
                ).body()!!
            )

            val articles = articlesResponseBody.articles.distinctBy { it.title }
                .filterNot { it.title == "[Removed]" }

            println("Articles size is ${articles.size}")

            LoadResult.Page(
                data = articles,
                prevKey = null,
                nextKey = if(articles.isEmpty()) null else pageNumber + 1
            )
        } catch (e : Exception) {
            LoadResult.Error(e)
        }
    }
}