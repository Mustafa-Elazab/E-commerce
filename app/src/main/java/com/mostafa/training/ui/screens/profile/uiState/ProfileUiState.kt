package com.mostafa.training.ui.screens.profile.uiState

import com.mostafa.training.data.remote.dto.ProfileData

data class ProfileUiState(
    val isLoading: Boolean = false,
    val error: String = "",
    val profileData: ProfileData? = null

)
