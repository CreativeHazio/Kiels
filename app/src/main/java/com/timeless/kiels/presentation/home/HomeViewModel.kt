package com.timeless.kiels.presentation.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.timeless.kiels.domain.model.Article
import com.timeless.kiels.domain.usecases.GetArticlesUseCase
import com.timeless.kiels.domain.usecases.StarredArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getArticlesUseCase: GetArticlesUseCase,
    private val starredArticlesUseCase: StarredArticlesUseCase
) : ViewModel() {

    private val _state = mutableStateOf(HomeState())
    val state : State<HomeState> = _state

    init {
        getArticles()
    }

    fun onEvent(event: HomeEvent) {
        when(event) {
            is HomeEvent.StarArticle -> starArticle(event.isStarred, event.article)
            HomeEvent.GetArticles -> getArticles()
        }
    }

    private fun getArticles() {
        viewModelScope.launch {
            _state.value = _state.value.copy(
                articles = getArticlesUseCase.getLatestArticles("technology")
                    .distinctUntilChanged().cachedIn(viewModelScope)
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