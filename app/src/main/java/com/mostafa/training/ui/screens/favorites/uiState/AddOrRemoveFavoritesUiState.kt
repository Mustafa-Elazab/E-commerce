package com.mostafa.training.ui.screens.favorites.uiState

import com.mostafa.training.data.remote.dto.AddOrRemoveFavoritesDTO
import com.mostafa.training.data.remote.dto.AddRemoveCartItemDTO

data class AddOrRemoveFavoritesUiState(
    val addOrRemoveFavoritesDTO: AddOrRemoveFavoritesDTO? = null,
    val isLoading: Boolean = false,
    val error: String = ""
)
