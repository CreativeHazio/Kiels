package com.timeless.kiels.core.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import java.net.SocketTimeoutException
import java.net.UnknownHostException

@Composable
fun ErrorScreen(
    error: Throwable
) {
    
    when(error) {
        is UnknownHostException -> ShowErrorScreen(errorMessage = "No internet connection")
        is SocketTimeoutException -> ShowErrorScreen(errorMessage = "Network timed out") // Show a snack bar to retry
    }

}

@Composable
fun ShowErrorScreen(
    errorMessage : String
) {
    
    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = errorMessage, style = MaterialTheme.typography.bodyLarge)
    }
    
}