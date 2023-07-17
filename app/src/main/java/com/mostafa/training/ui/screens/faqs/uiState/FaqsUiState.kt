package com.mostafa.training.ui.screens.faqs.uiState

import com.mostafa.training.data.remote.dto.FaqsDTO

data class FaqsUiState(
    val isLoading: Boolean = false,
    val error: String = "",
    val data: FaqsDTO? = null
)
