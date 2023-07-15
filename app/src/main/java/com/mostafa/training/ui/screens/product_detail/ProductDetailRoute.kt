package com.mostafa.training.ui.screens.product_detail

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument


val ROUTE = "productDetailScreen"


fun NavController.navigateToDetailScreen(id: Int) {
    navigate("${ROUTE}/$id")
}


fun NavGraphBuilder.productDetailRoute(navController:NavController) {
    composable(
        "$ROUTE/{${ProductDetailArgs.NAME_ARG}}",
        arguments = listOf(
            navArgument(ProductDetailArgs.NAME_ARG) {
                NavType.IntType
            }
        )
    ) {
        ProductDetailScreen(navController = navController)
    }
}

class ProductDetailArgs(savedStateHandle: SavedStateHandle) {

    val id: Int = checkNotNull(savedStateHandle[NAME_ARG]).toString().toInt()

    companion object {
        const val NAME_ARG = "id"
    }
}