package com.mostafa.training.ui.screens.notification.uiState

import com.mostafa.training.data.remote.dto.NotificationDTO

data class NotificationUiState(
    val error: String = "",
    val isLoading: Boolean = false,
    val notifications: List<NotificationDTO> = emptyList()
)