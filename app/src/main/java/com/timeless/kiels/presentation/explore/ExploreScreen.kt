package com.timeless.kiels.presentation.explore

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.timeless.kiels.R
import com.timeless.kiels.core.components.ScreenHeadline
import com.timeless.kiels.ui.theme.DarkBlue
import com.timeless.kiels.ui.theme.Gray
import com.timeless.kiels.ui.theme.LightBlue
import com.timeless.kiels.ui.theme.Transparent
import com.timeless.kiels.ui.theme.TransparentDarkBlue
import com.timeless.kiels.ui.theme.TransparentLightBlue
import java.util.stream.Collector.Characteristics
import kotlin.math.truncate

@Composable
fun ExploreScreenRoot(viewModel: ExploreViewModel) {

    ExploreScreen(
        viewModel.categoryList,
        viewModel.state.value,
        viewModel::onEvent
    )
}

@Composable
fun ExploreScreen(
    categoryList: List<String>,
    state: ExploreState,
    onEvent: (ExploreEvent) -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 60.dp),
    ) {

        ScreenHeadline(
            title = stringResource(id = R.string.explore),
            paddingTop = 60.dp
        )
        
        Spacer(modifier = Modifier.size(20.dp))

        SearchSection(state, onEvent)

        Spacer(modifier = Modifier.size(40.dp))

        CategorySection(categoryList)

    }

}

@Composable
fun SearchSection(
    state: ExploreState,
    onEvent: (ExploreEvent) -> Unit
) {
    
    Text(
        modifier = Modifier.padding(start = 10.dp),
        text = "Explore Articles ${String(Character.toChars(0x1F35F))}",
        style = MaterialTheme.typography.titleLarge
    )
    Spacer(modifier = Modifier.size(10.dp))
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(start = 10.dp, end = 10.dp)
            .clip(RoundedCornerShape(8.dp)),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = if (isSystemInDarkTheme()) TransparentDarkBlue else TransparentLightBlue,
            unfocusedContainerColor = if (isSystemInDarkTheme()) TransparentDarkBlue else TransparentLightBlue,
            focusedTrailingIconColor = MaterialTheme.colorScheme.secondary,
            unfocusedTrailingIconColor = MaterialTheme.colorScheme.secondary,
            unfocusedIndicatorColor = if (isSystemInDarkTheme()) TransparentDarkBlue else TransparentLightBlue,
            cursorColor = MaterialTheme.colorScheme.secondary,
            selectionColors = TextSelectionColors(MaterialTheme.colorScheme.secondary,MaterialTheme.colorScheme.secondary),
        ),
        trailingIcon = {
            IconButton(onClick = { onEvent(ExploreEvent.SearchArticle) }) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
            }
        },
        placeholder = {
            Text(
                text = stringResource(id = R.string.search_bar_placeholder_text),
                color = TransparentDarkBlue
            )
        },
        singleLine = true,
        value = state.searchQuery,
        onValueChange = { onEvent(ExploreEvent.UpdateSearchQuery(it)) }
    )

}

@Preview(showBackground = true)
@Composable
fun SearchPreview() {
    SearchSection(state = ExploreState()) {

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategorySection(
    categoryList : List<String>
) {
    
    Column {
        Text(
            modifier = Modifier.padding(start = 10.dp),
            text = stringResource(id = R.string.categories),
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.size(10.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(2)
        ) {
            items(
                items = categoryList,
            ) {
                Card(
                    modifier = Modifier
                        .height(100.dp)
                        .padding(8.dp),
                    colors = CardDefaults.cardColors(
                       containerColor = if (isSystemInDarkTheme()) TransparentDarkBlue else TransparentLightBlue
                    ),
                    shape = RoundedCornerShape(5.dp),
                    onClick = { /*TODO*/ }
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = it,
                            style = MaterialTheme.typography.titleLarge,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }
    }
    
}