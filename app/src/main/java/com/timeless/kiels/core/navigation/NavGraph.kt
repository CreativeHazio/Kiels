package com.timeless.kiels.core.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.timeless.kiels.presentation.explore.ExploreScreenRoot
import com.timeless.kiels.presentation.explore.ExploreViewModel
import com.timeless.kiels.presentation.home.HomeScreenRoot
import com.timeless.kiels.presentation.home.HomeViewModel
import com.timeless.kiels.presentation.setup.SetupScreen
import com.timeless.kiels.presentation.starred.StarredScreenRoot
import com.timeless.kiels.presentation.starred.StarredViewModel

@Composable
fun Navigation(navHostController: NavHostController) {

    NavHost(
        navController = navHostController,
        startDestination = Screen.HomeScreen.route,
        enterTransition = {
            fadeIn(animationSpec = tween(
                300, easing = LinearEasing
            )) + slideIntoContainer(
                animationSpec = tween(300, easing = EaseIn),
                towards = AnimatedContentTransitionScope.SlideDirection.Start
            )
        },
        exitTransition = {
            fadeOut(animationSpec = tween(
                300, easing = LinearEasing
            )) + slideOutOfContainer(
                animationSpec = tween(300, easing = EaseOut),
                towards = AnimatedContentTransitionScope.SlideDirection.End
            )
        }
    ) {

        composable(Screen.HomeScreen.route) {
            val viewModel : HomeViewModel = hiltViewModel()
            HomeScreenRoot(viewModel)
        }

        composable(Screen.ExploreScreen.route) {
            val viewModel : ExploreViewModel = hiltViewModel()
            ExploreScreenRoot(viewModel)
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