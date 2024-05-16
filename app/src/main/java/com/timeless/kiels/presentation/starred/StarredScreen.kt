package com.timeless.kiels.presentation.starred

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.timeless.kiels.domain.model.Article
import com.timeless.kiels.presentation.shared.StarredScreenArticleCardList

@Composable
fun StarredScreenRoot(viewModel: StarredViewModel) {

    val articles = viewModel.starredArticles.collectAsState().value

    StarredScreen(articles)
}

@Composable
fun StarredScreen(
    articles : List<Article>,
) {

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {

        Column(
            modifier = Modifier.padding(top = 30.dp, start = 10.dp)
        ) {
            Text(text = "Starred", style = MaterialTheme.typography.headlineLarge)
            Divider(
                thickness = 6.dp,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier
                    .width(40.dp)
                    .padding(top = 5.dp)
            )
        }

        Spacer(modifier = Modifier.size(20.dp))
        StarredArticlesSection(articles = articles)
    }

}

@Composable
fun StarredArticlesSection(
    articles: List<Article>
) {
    StarredScreenArticleCardList(articles = articles)
}