package com.mostafa.training.ui.screens.home.ui_state

import com.mostafa.training.data.remote.dto.CategoryDTO

data class CategoryUiState(
    val categories: List<CategoryDTO?>? = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
)