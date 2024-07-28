package com.timeless.kiels.presentation.explore

sealed class ExploreEvent {

    data class UpdateSearchQuery(val searchQuery: String) : ExploreEvent()
    data object SearchArticle : ExploreEvent()

}