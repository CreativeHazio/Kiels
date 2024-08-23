package com.timeless.kiels.presentation.explore

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.timeless.kiels.domain.model.Article
import com.timeless.kiels.domain.usecases.GetArticlesUseCase
import com.timeless.kiels.domain.usecases.StarredArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(
    private val getArticlesUseCase: GetArticlesUseCase,
    private val starredArticlesUseCase: StarredArticlesUseCase
) : ViewModel() {

    private val _state = mutableStateOf(ExploreState())
    val state : State<ExploreState> = _state

    val categoryList = listOf("Blockchain","UI/UX","Programming","Startups","Big Tech")

    fun onEvent(event: ExploreEvent) {
        when(event) {
            ExploreEvent.ExploreArticle -> {
                searchArticles()
            }
            is ExploreEvent.UpdateSearchQuery -> {
                _state.value = _state.value.copy(searchQuery = event.searchQuery)
            }

            is ExploreEvent.StarArticle -> {
                starArticle(event.isStarred, event.article)
            }
        }
    }

    private fun searchArticles() {
        viewModelScope.launch {
            println("Is loading articles from search")
            _state.value = _state.value.copy(isLoading = true)
            val articles = getArticlesUseCase.exploreArticles(
                _state.value.searchQuery
            ).cachedIn(viewModelScope)
            _state.value = _state.value.copy(
                articles = articles,
                isLoading = false
            )
        }
    }

    private fun starArticle(isStarred : Boolean, article: Article) {
        // TODO: If isStarred then delete article from room, else add article to room
        viewModelScope.launch(Dispatchers.IO) {
            if (!isStarred) {
                starredArticlesUseCase.deleteStarredArticle(article.copy(
                    isStarred = false
                ))
            } else {
                starredArticlesUseCase.saveStarredArticle(article.copy(
                    isStarred = true
                ))
            }
        }
    }

}