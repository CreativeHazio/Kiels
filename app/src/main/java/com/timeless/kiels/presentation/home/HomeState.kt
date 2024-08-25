package com.timeless.kiels.presentation.home

import androidx.paging.PagingData
import com.timeless.kiels.domain.model.Article
import kotlinx.coroutines.flow.Flow

data class HomeState(
    val articles : Flow<PagingData<Article>>? = null
)
