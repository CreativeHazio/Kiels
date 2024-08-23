package com.timeless.kiels.presentation.explore

import androidx.paging.PagingData
import androidx.room.Query
import com.timeless.kiels.domain.model.Article
import kotlinx.coroutines.flow.Flow

data class ExploreState(
    val searchQuery: String = "",
    val articles : Flow<PagingData<Article>>? = null,
    val isLoading : Boolean = false
)
