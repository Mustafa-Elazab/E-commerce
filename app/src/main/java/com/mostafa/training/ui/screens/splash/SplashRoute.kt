package com.mostafa.training.ui.screens.splash

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val ROUTE = "splashScreen"

fun NavGraphBuilder.splashScreenRoute(navController: NavController) {
    composable(ROUTE) {
        SplashScreen(navController = navController)
    }
}