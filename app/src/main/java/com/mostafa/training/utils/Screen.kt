package com.mostafa.training.utils


import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.mostafa.training.R

sealed class Screen(
    val route: String,
    @StringRes val title: Int,
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unSelectedIcon: Int,
    ) {

   object Home : Screen(
      route = "homeScreen",
      title = R.string.home,
      unSelectedIcon = R.drawable.ic_unselected_home,
      selectedIcon = R.drawable.ic_selected_home
   )

   object Search : Screen(
      route = "search",
      title = R.string.search,
      unSelectedIcon = R.drawable.ic_search,
      selectedIcon = R.drawable.ic_search
   )

   object Cart : Screen(
      route = "cartScreen",
      title = R.string.cart,
      unSelectedIcon = R.drawable.ic_unselected_cart,
      selectedIcon = R.drawable.ic_selected_cart
   )

   object Profile : Screen(
      route = "profileScreen",
      title = R.string.profile,
      unSelectedIcon = R.drawable.ic_unselected_profile,
      selectedIcon = R.drawable.ic_selected_profile
   )
}
