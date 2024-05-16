package com.timeless.kiels.presentation.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.timeless.kiels.domain.model.Article
import com.timeless.kiels.domain.usecases.GetArticlesUseCase
import com.timeless.kiels.domain.usecases.StarredArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getArticlesUseCase: GetArticlesUseCase,
    private val starredArticlesUseCase: StarredArticlesUseCase
) : ViewModel() {

    private val _keyword = MutableStateFlow("")
    val keyword = _keyword.asStateFlow()

    val articlesHeadline = getArticlesUseCase.getArticlesHeadline(
        ""
    ).distinctUntilChanged().cachedIn(viewModelScope)

    val articles = getArticlesUseCase.getLatestArticles(
        "technology"
    ).distinctUntilChanged().cachedIn(viewModelScope)

    fun starArticle(isStarred : Boolean, article: Article) {
        // TODO: If isStarred then delete article from room, else add article to room
        viewModelScope.launch(Dispatchers.IO) {
            if (!isStarred) {
                starredArticlesUseCase.deleteStarredArticle(article)
            } else {
                starredArticlesUseCase.saveStarredArticle(article)
            }
        }
    }

}