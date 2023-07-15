package com.mostafa.training.ui.screens.home.ui_state

import com.mostafa.training.data.remote.dto.ProductDTO

data class ProductsUiState(
    val products: List<ProductDTO> = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
)
