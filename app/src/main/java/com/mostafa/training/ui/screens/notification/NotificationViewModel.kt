package com.mostafa.training.ui.screens.notification

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mostafa.training.data.remote.response.NetworkResponse
import com.mostafa.training.domain.usecase.GetNotificationsUseCase
import com.mostafa.training.ui.screens.notification.uiState.NotificationUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NotificationViewModel(private val notificationsUseCase: GetNotificationsUseCase) :
    ViewModel() {

    private val _notificationsUiState = MutableStateFlow(NotificationUiState())
    val notificationsUiState = _notificationsUiState.asStateFlow()


    init {
        loadNotifications(authorization = "b676yF4HQTAGtP9bYNM2kjAw3VZ6vd63Ar7dr7jQvhISokVKIK5K3Emr4tiPctOBgBlZhV")
    }


    private fun loadNotifications(authorization: String) {
        viewModelScope.launch {
            _notificationsUiState.update { it.copy(isLoading = true) }
            when (val response = notificationsUseCase.invoke(authorization = authorization)) {
                is NetworkResponse.ApiError -> {
                    _notificationsUiState.update {
                        NotificationUiState(
                            error = response.body.message.toString(),
                            isLoading = false
                        )
                    }
                }

                is NetworkResponse.NetworkError -> {
                    _notificationsUiState.update {
                        NotificationUiState(
                            error = response.error.message.toString(),
                            isLoading = false
                        )
                    }
                }

                is NetworkResponse.Success -> {
                    _notificationsUiState.update {
                        NotificationUiState(
                            notifications = response.body.data?.data!!,
                            isLoading = false
                        )
                    }
                }

                is NetworkResponse.UnknownError -> {
                    _notificationsUiState.update {
                        NotificationUiState(
                            error = response.error!!.message.toString(),
                            isLoading = false
                        )
                    }
                }

                else -> {

                }
            }
        }
    }
}