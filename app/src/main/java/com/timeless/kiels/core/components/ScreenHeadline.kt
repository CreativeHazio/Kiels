package com.timeless.kiels.core.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ScreenHeadline(
    title : String,
    paddingTop : Dp
) {

    Column (
        horizontalAlignment = Alignment.Start,
        modifier = Modifier.padding(top = paddingTop, start = 10.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineLarge
        )
        Divider(
            thickness = 6.dp,
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier
                .width(40.dp)
                .padding(top = 5.dp)
        )
    }

}