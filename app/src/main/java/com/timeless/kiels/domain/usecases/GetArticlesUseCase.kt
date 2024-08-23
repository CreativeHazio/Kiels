package com.timeless.kiels.domain.usecases

data class GetArticlesUseCase(
    val getLatestArticles: GetLatestArticles,
    val exploreArticles: ExploreArticles
)

