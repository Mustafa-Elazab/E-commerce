package com.mostafa.training.ui.screens.faqs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mostafa.training.ui.screens.contact.ContactScreen

const val ROUTE="faqsScreen"

fun NavController.navigateToFaqsScreen(){
    navigate(ROUTE)
}

fun NavGraphBuilder.faqsScreenRoute(navController: NavController){
    composable(ROUTE){
        FaqsScreen(navController = navController)
    }
}