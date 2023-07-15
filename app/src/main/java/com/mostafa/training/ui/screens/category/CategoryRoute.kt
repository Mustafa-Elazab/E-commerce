package com.mostafa.training.ui.screens.category

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable


const val ROUTE="categoryScreen"

fun NavController.navigateToCategoryScreen(){
    navigate(ROUTE)
}

fun NavGraphBuilder.categoryScreenRoute(navController: NavController){
    composable(ROUTE){
        CategoryScreen(navController = navController)
    }
}