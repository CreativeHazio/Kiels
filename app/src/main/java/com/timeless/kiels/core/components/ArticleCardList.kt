package com.timeless.kiels.core.components

import android.graphics.Bitmap
import android.view.MotionEvent
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.timeless.kiels.R
import com.timeless.kiels.domain.model.Article
import com.timeless.kiels.presentation.starred.StarredViewModel
import com.timeless.kiels.ui.theme.DividerGray
import com.timeless.kiels.ui.theme.Gray

@Composable
fun HomeScreenArticleCardList(
    articles : LazyPagingItems<Article>,
    starArticle: (Boolean, Article) -> Unit
) {

    val handlePagingResult = handlePagingResults(articles = articles)

    if (handlePagingResult) {

        LazyColumn{
            items(count = articles.itemCount) {
                articles[it]?.let { article ->
                    ArticleCard(article = article) { isStarred->
                        starArticle(isStarred, article)
                    }
                }
            }
            item {
                if (articles.loadState.append is LoadState.Loading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(20.dp),
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }
        }
    }

}

@Composable
fun StarredScreenArticleCardList(
    articles : List<Article>,
) {

    val starredViewModel : StarredViewModel = hiltViewModel()

    LazyColumn {
        items(
            items = articles,
            key = { article -> article.url!! }
        ) { article ->
            ArticleCard(article = article) { isUnstarred ->
                if (isUnstarred) starredViewModel.deleteStarredArticle(article)
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleCard(
    article: Article,
    starOnClick: (Boolean) -> Unit
) {

    val unStarredIcon = painterResource(id = R.drawable.ic_unstar)
    val starredIcon = painterResource(id = R.drawable.ic_star)

    var expandedState by remember {
        mutableStateOf(false)
    }

    var starredIconClicked by remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current

    Surface(
        color = MaterialTheme.colorScheme.surface,
        modifier = Modifier.fillMaxWidth()
    ) {

        Divider(
            thickness = 0.3.dp,
            color = if (isSystemInDarkTheme()) DividerGray else Gray,
            modifier = Modifier.fillMaxWidth()
        )

        Card(
            modifier = Modifier
                .animateContentSize(
                    animationSpec = TweenSpec(
                        durationMillis = 150,
                        easing = LinearOutSlowInEasing
                    )
                )
                .height(if (expandedState) 600.dp else 200.dp)
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            shape = RectangleShape,
            onClick = {
                expandedState = !expandedState
            }
        ) {
            Column(
                modifier = Modifier
                    .padding(top = 20.dp, start = 10.dp, end = 10.dp, bottom = 20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        modifier = Modifier
                            .size(80.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .weight(1f),
                        contentScale = ContentScale.Crop,
                        model = ImageRequest.Builder(context).data(article.urlToImage).build(),
                        contentDescription = "Article Image"
                    )
                    Text(
                        text = article.title ?: "",
                        style = MaterialTheme.typography.titleLarge,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 3,
                        modifier = Modifier
                            .weight(3f)
                            .padding(start = 10.dp)
                    )
                    IconButton(
                        modifier = Modifier.weight(1f),
                        onClick = {
                            starredIconClicked = !starredIconClicked
                            starOnClick(starredIconClicked)
                        }
                    ) {
                        Icon(
                            painter = if (starredIconClicked || article.isStarred) starredIcon else unStarredIcon,
                            tint = if (starredIconClicked || article.isStarred) {
                                MaterialTheme.colorScheme.secondary
                            } else {
                                Color.Unspecified
                            },
                            modifier = Modifier.size(24.dp),
                            contentDescription = "Read later"
                        )
                    }
                }
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = article.description ?: "",
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyLarge
                )
                if (expandedState) {
                    Spacer(modifier = Modifier.size(10.dp))
                    AndroidView(
                        modifier = Modifier.verticalScroll(
                            state = rememberScrollState(),
                            enabled = true
                        ),
                        factory = {context ->
                            WebView(context).apply {
                                clipToOutline = true
                                webViewClient = WebViewClient()
                            }
                        },
                        update = {
                            it.loadUrl(article.url ?: "")
                        }
                    )

                }
            }
        }

    }

}

@Composable
fun handlePagingResults(articles: LazyPagingItems<Article>) : Boolean {
    val loadState = articles.loadState


    val error = when {
        loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
        loadState.append is LoadState.Error -> loadState.append as LoadState.Error
        loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
        else -> null
    }

    return when {
        loadState.refresh is LoadState.Loading -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(40.dp),
                    color = MaterialTheme.colorScheme.secondary
                )
            }
            false
        }

        error != null -> {
            println("Error: ${error.error}")
            ErrorScreen(error = error.error)
            false
        }

        else -> {
            true
        }
    }
}