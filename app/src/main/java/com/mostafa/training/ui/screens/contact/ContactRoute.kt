package com.mostafa.training.ui.screens.contact

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val ROUTE="contactScreen"

fun NavController.navigateToContactScreen(){
    navigate(ROUTE)
}

fun NavGraphBuilder.contactScreenRoute(navController: NavController){
    composable(ROUTE){
        ContactScreen(navController = navController)
    }
}