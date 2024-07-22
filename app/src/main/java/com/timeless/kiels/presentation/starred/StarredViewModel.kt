package com.timeless.kiels.presentation.starred

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.timeless.kiels.domain.model.Article
import com.timeless.kiels.domain.usecases.StarredArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StarredViewModel @Inject constructor(
    private val starredArticlesUseCase: StarredArticlesUseCase
) : ViewModel() {

    private val _starredArticles = MutableStateFlow<List<Article>>(emptyList())
    val starredArticles = _starredArticles.asStateFlow()

    init {
        getStarredArticles()
    }

    private fun getStarredArticles() {
        viewModelScope.launch(Dispatchers.IO) {
            starredArticlesUseCase.getStarredArticles().collectLatest {
                _starredArticles.value = it
            }
        }
    }

    fun deleteStarredArticle(article : Article) {
        viewModelScope.launch(Dispatchers.IO) {
            starredArticlesUseCase.deleteStarredArticle(article.copy(isStarred = false))
        }
    }


}