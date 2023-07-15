package com.mostafa.training.ui.screens.cart.uiState

import com.mostafa.training.data.remote.dto.CartDTO
import com.mostafa.training.data.remote.dto.CartDataDTO

data class CartUiState(
    val cartData: CartDataDTO? = null,
    val isLoading: Boolean = false,
    val error: String = ""
)
