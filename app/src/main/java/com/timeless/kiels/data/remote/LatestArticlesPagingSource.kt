package com.timeless.kiels.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.timeless.kiels.data.mapper.ArticlesMapper
import com.timeless.kiels.domain.model.Article
import javax.inject.Inject

class LatestArticlesPagingSource @Inject constructor(
    private val articlesAPI: ArticlesAPI,
    private val keyword : String
) : PagingSource<Int, Article>() {
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { position ->
            val page = state.closestPageToPosition(position)
            page?.prevKey?.minus(1) ?: page?.nextKey?.plus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            val pageIndex = params.key ?: 1
            val articlesResponse = articlesAPI.getLatestArticlesFromNewsApi(
                keyword = keyword, pageNumber = pageIndex
            )

            val articles = ArticlesMapper.fromArticlesResponseToArticlesBody(
                articlesResponse.body()!!
            ).articles.distinctBy { it.title }.filterNot { it.title == "[Removed]" }
            LoadResult.Page(
                data = articles,
                prevKey = null,
                nextKey = if (articles.isEmpty()) null else pageIndex + 1
            )
        } catch (e : Exception) {
            LoadResult.Error(e)
        }
    }
}