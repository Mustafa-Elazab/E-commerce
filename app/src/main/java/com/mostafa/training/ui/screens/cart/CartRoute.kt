package com.mostafa.training.ui.screens.cart

import androidx.compose.ui.unit.Dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mostafa.training.utils.Screen


fun NavGraphBuilder.cartRoute(
    calculateBottomPadding: Dp,
    navController: NavController
) {
    composable(Screen.Cart.route) {
        CartScreen(
            calculateBottomPadding = calculateBottomPadding,
            navController = navController
        )
    }
}