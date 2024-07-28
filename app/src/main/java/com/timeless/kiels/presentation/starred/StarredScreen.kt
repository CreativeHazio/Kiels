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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.timeless.kiels.R
import com.timeless.kiels.core.components.ScreenHeadline
import com.timeless.kiels.core.components.StarredScreenArticleCardList
import com.timeless.kiels.domain.model.Article

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
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 60.dp),
    ) {

        ScreenHeadline(
            title = stringResource(id = R.string.starred),
            paddingTop = 60.dp
        )

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