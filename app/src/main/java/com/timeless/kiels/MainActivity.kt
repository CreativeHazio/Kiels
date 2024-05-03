package com.timeless.kiels

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.timeless.kiels.presentation.navigation.BottomNavItem
import com.timeless.kiels.presentation.navigation.BottomNavigationBar
import com.timeless.kiels.presentation.navigation.Navigation
import com.timeless.kiels.presentation.navigation.Screen
import com.timeless.kiels.ui.theme.KielsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            KielsTheme {
                val systemUiController = rememberSystemUiController()
                val isDarkMode = isSystemInDarkTheme()

                systemUiController.setStatusBarColor(MaterialTheme.colorScheme.primary)

                systemUiController.statusBarDarkContentEnabled = !isDarkMode
                val navController = rememberNavController()

                Scaffold(
                    bottomBar = {
                        BottomNavigationBar(
                            items = listOf(
                                BottomNavItem("Home", Icons.Default.Home, Screen.HomeScreen.route),
                                BottomNavItem("Explore", Icons.Default.Search, Screen.ExploreScreen.route),
                                BottomNavItem("Starred", Icons.Default.Favorite, Screen.StarredScreen.route),
                                BottomNavItem("Setup", Icons.Default.Settings, Screen.SetupScreen.route)
                            ),
                            navController = navController
                        )
                    }
                ) {
                    Navigation(navHostController = navController)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScreensPreview() {
    KielsTheme {

    }
}
