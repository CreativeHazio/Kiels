package com.timeless.kiels.presentation.explore

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.timeless.kiels.domain.usecases.GetArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(
    private val getArticlesUseCase: GetArticlesUseCase
) : ViewModel() {

    private val _state = mutableStateOf(ExploreState())
    val state : State<ExploreState> = _state

    val categoryList = listOf("Blockchain","UI/UX","Programming","Startups","Big Tech")

    fun onEvent(event: ExploreEvent) {
        when(event) {
            ExploreEvent.SearchArticle -> {
                searchArticles()
            }
            is ExploreEvent.UpdateSearchQuery -> {
                _state.value = _state.value.copy(searchQuery = event.searchQuery)
            }
        }
    }

    private fun searchArticles() {
        viewModelScope.launch {
            val articles = getArticlesUseCase.getLatestArticles(
                _state.value.searchQuery
            ).cachedIn(viewModelScope)
            _state.value = _state.value.copy(
                articles = articles
            )
        }
    }

}