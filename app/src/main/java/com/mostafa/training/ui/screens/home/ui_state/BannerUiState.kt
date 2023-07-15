package com.mostafa.training.ui.screens.home.ui_state

import com.mostafa.training.data.remote.dto.BannerDTO

data class BannerUiState(
    val banners: List<BannerDTO?>? = emptyList(),
    val isLoading: Boolean = false,
    val error: String = ""
)