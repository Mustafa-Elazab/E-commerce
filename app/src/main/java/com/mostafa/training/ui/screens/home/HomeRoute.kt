package com.mostafa.training.ui.screens.home

import androidx.compose.ui.unit.Dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mostafa.training.utils.Screen


fun NavGraphBuilder.homeRoute(
    calculateBottomPadding: Dp,
    navController: NavController
) {
    composable(Screen.Home.route) {
        HomeScreen(
            calculateBottomPadding = calculateBottomPadding,
            navController = navController,

        )
    }
}