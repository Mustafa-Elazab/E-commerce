package com.mostafa.training.ui.screens.product_detail.uiState

import com.mostafa.training.data.remote.dto.ProductsDetailDTO

data class ProductDetailUiState(
    val product: ProductsDetailDTO? = null,
    val isLoading: Boolean = false,
    val error: String = ""
)