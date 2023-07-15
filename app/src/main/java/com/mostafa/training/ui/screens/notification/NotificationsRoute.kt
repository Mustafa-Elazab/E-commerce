package com.mostafa.training.ui.screens.notification

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable


private const val ROUTE = "notificationsScreen"

fun NavController.navigateToNotifications(){
    navigate(ROUTE)
}

fun NavGraphBuilder.notificationsRoute(navController: NavController){
    composable(ROUTE){
        NotificationsScreen(navController = navController)
    }
}