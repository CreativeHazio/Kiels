package com.timeless.kiels.domain.usecases

data class StarredArticlesUseCase(
    val saveStarredArticle: SaveStarredArticle,
    val deleteStarredArticle: DeleteStarredArticle,
    val getStarredArticles: GetStarredArticles
)