package com.timeless.kiels.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.timeless.kiels.R
import com.timeless.kiels.core.components.HomeScreenArticleCardList
import com.timeless.kiels.core.components.ScreenHeadline
import com.timeless.kiels.domain.model.Article

@Composable
fun HomeScreenRoot(
    viewModel: HomeViewModel
) {

    // TODO: Also get user detail from viewmodel
//    val articles : LazyPagingItems<Article> = viewModel.articles.collectAsLazyPagingItems()
    val state = viewModel.state
    val event = viewModel::onEvent

    state.value.articles?.let {
        HomeScreen(articles = it.collectAsLazyPagingItems()) { isStarred, article ->
            event(HomeEvent.StarArticle(isStarred, article))
        }
    }

}

@Composable
fun HomeScreen(
//    profileImageUrl : String,
//    articlesHeadline : List<Article>,
    articles : LazyPagingItems<Article>,
    starArticle: (Boolean, Article) -> Unit
) {

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        ProfileSection()
        Spacer(modifier = Modifier.height(20.dp))
        NewsSection(articles = articles, starArticle = starArticle)
    }

}


@Composable
fun ProfileSection(
//    profileImageUrl : String
) {

    Column(
        horizontalAlignment = Alignment.End,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, end = 20.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.hazio),
            contentDescription = stringResource(R.string.user_profile_image),
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
    }

    ScreenHeadline(
        title = stringResource(R.string.kiels_articles_for_you),
        paddingTop = 10.dp
    )

}

@Composable
fun NewsSection(
    articles: LazyPagingItems<Article>,
    starArticle: (Boolean, Article) -> Unit
) {

    HomeScreenArticleCardList(articles = articles, starArticle = starArticle)

}