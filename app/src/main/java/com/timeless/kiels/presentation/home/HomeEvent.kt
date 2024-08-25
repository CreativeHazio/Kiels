package com.timeless.kiels.presentation.home

import com.timeless.kiels.domain.model.Article

sealed class HomeEvent {
    data class StarArticle(val isStarred : Boolean,val article: Article) : HomeEvent()
    data object GetArticles : HomeEvent()
}