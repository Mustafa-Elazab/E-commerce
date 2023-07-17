package com.mostafa.training.ui.screens.favorites

import androidx.compose.ui.unit.Dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mostafa.training.ui.screens.cart.CartScreen
import com.mostafa.training.utils.Screen


const val ROUTE="favoritesScreen"

fun NavController.navigateToFavoritesScreen(){
    navigate(ROUTE)
}

fun NavGraphBuilder.favoritesScreenRoute(
    navController: NavController
){
    composable(ROUTE){
        FavoritesScreen(
            navController,
        )
    }
}
