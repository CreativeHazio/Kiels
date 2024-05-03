package com.timeless.kiels.presentation.shared

import androidx.compose.runtime.Composable
import java.net.UnknownHostException

@Composable
fun ErrorScreen(
    error: Throwable
) {
    
    when(error) {
        is UnknownHostException -> ShowErrorScreen(errorMessage = "")
    }

}

@Composable
fun ShowErrorScreen(
    errorMessage : String
) {
    
}