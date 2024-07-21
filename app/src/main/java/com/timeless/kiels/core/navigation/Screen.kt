package com.timeless.kiels.core.navigation

sealed class Screen (val route : String) {

    object HomeScreen : Screen("home_screen")

    object StarredScreen : Screen("starred_screen")

    object ExploreScreen : Screen("explore_screen")

    object SetupScreen : Screen("setup_screen")

}