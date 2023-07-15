package com.mostafa.training.ui.screens.cart.uiState

import com.mostafa.training.data.remote.dto.AddRemoveCartItemDTO
import com.mostafa.training.data.remote.dto.AddRemoveCartItemData
import com.mostafa.training.data.remote.dto.CartDTO

data class AddOrRemoveUiState(
    val addRemoveCartItemDTO: AddRemoveCartItemDTO? = null,
    val isLoading: Boolean = false,
    val error: String = ""
)
