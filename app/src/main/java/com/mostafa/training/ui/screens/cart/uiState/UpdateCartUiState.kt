package com.mostafa.training.ui.screens.cart.uiState

import com.mostafa.training.data.remote.dto.CartItemUpdateDTO

data class UpdateCartUiState(
    val cartUpdateData: CartItemUpdateDTO? = null,
    val isLoading: Boolean = false,
    val error: String = ""
)
