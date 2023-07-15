package com.mostafa.training.ui.screens.search

import androidx.compose.ui.unit.Dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mostafa.training.utils.Screen


fun NavController.navigateToSearchScreen() {
    popBackStack(Screen.Search.route, true)
    navigate(Screen.Search.route)
}

fun NavGraphBuilder.searchRoute(
    calculateBottomPadding: Dp,
    navController: NavController
) {
    composable(Screen.Search.route) {
        SearchScreen(
            calculateBottomPadding = calculateBottomPadding,
            navController = navController
        )
    }
}