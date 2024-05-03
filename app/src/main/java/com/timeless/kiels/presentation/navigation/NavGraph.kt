package com.timeless.kiels.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.timeless.kiels.presentation.explore.ExploreScreen
import com.timeless.kiels.presentation.home.HomeScreenRoot
import com.timeless.kiels.presentation.home.HomeViewModel
import com.timeless.kiels.presentation.setup.SetupScreen
import com.timeless.kiels.presentation.starred.StarredScreenRoot
import com.timeless.kiels.presentation.starred.StarredViewModel

@Composable
fun Navigation(navHostController: NavHostController) {

    NavHost(navController = navHostController, startDestination = Screen.HomeScreen.route) {

        composable(Screen.HomeScreen.route) {
            val viewModel : HomeViewModel = hiltViewModel()
            HomeScreenRoot(viewModel)
        }

        composable(Screen.ExploreScreen.route) {
            ExploreScreen()
        }

        composable(Screen.StarredScreen.route) {
            val viewModel : StarredViewModel = hiltViewModel()
            StarredScreenRoot(viewModel)
        }

        composable(Screen.SetupScreen.route) {
            SetupScreen()
        }


    }
}