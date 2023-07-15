package com.mostafa.training.ui.screens.productsByCategory

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

const val ROUTE = "productsByCategoryScreen"

fun NavController.navigateToProductsByCategoryScreen(id: Int) {
    navigate("$ROUTE/$id")
}

fun NavGraphBuilder.productsByCategoryScreenRoute(navController: NavController) {
    composable("$ROUTE/{${ProductsByCategoryArgs.ID_ARG}}",
        arguments = listOf(
            navArgument(ProductsByCategoryArgs.ID_ARG) {
                NavType.IntType
            },


        )
    ) {
        ProductsByCategoryScreen(navController = navController)
    }


}

class ProductsByCategoryArgs(savedStateHandle: SavedStateHandle) {
    val id: Int = checkNotNull(savedStateHandle[ID_ARG]).toString().toInt()


    companion object {
        const val ID_ARG = "id"
    }
}

