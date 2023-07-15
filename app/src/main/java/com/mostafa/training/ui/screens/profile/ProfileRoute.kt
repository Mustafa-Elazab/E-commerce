package com.mostafa.training.ui.screens.profile

import androidx.compose.ui.unit.Dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mostafa.training.utils.Screen



fun NavGraphBuilder.profileRoute(
    calculateBottomPadding: Dp,
    navController: NavController
) {
    composable(Screen.Profile.route) {
        ProfileScreen(
            calculateBottomPadding = calculateBottomPadding,
            navController = navController
        )
    }
}