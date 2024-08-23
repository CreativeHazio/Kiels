package com.timeless.kiels.presentation.explore

import com.timeless.kiels.domain.model.Article

sealed class ExploreEvent {

    data class UpdateSearchQuery(val searchQuery: String) : ExploreEvent()
    data object ExploreArticle : ExploreEvent()
    data class StarArticle(val isStarred : Boolean,val article: Article) : ExploreEvent()

}