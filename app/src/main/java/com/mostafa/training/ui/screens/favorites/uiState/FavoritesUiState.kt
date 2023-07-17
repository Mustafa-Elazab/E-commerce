package com.mostafa.training.ui.screens.favorites.uiState

import com.mostafa.training.data.remote.dto.FavoritesDTO

data class FavoritesUiState(
    val isLoading: Boolean = false,
    val error: String = "",
    val data: FavoritesDTO? = null
)
