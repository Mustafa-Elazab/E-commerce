package com.mostafa.training.ui.screens.product

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable


const val ROUTE="productsScreen"

fun NavController.navigateToProductsScreen() {
    navigate(ROUTE)
}

fun NavGraphBuilder.productsScreenRoute(navController: NavController){
    composable(ROUTE){
        ProductsScreen(navController = navController)
    }
}