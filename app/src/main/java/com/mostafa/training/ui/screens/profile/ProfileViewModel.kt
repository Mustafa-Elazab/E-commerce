package com.mostafa.training.ui.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mostafa.training.data.remote.response.NetworkResponse
import com.mostafa.training.domain.usecase.GetProfileUseCase
import com.mostafa.training.ui.screens.profile.uiState.ProfileUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel(private val profileUseCase: GetProfileUseCase) : ViewModel() {


    private val _profileUiState = MutableStateFlow(ProfileUiState())
    val profileUiState get() = _profileUiState.asStateFlow()

    init {
        loadProfile()
    }

    private fun loadProfile() {

        viewModelScope.launch {
            _profileUiState.update { it.copy(isLoading = true) }
            when (val response =
                profileUseCase.invoke(authorization = "NN8p3D6X3dQL0GllyvSSQwY4J4v2fDQ8wIdQWDrnVGNoYrDMpkdVuLgEN6HULhotByHqjK")) {
                is NetworkResponse.ApiError -> {
                    _profileUiState.update {
                        ProfileUiState(
                            isLoading = false,
                            error = response.body.message.toString()
                        )
                    }
                }
                is NetworkResponse.NetworkError ->{
                    _profileUiState.update {
                        ProfileUiState(
                            isLoading = false,
                            error = response.error.message.toString()
                        )
                    }
                }
                is NetworkResponse.Success -> {
                    _profileUiState.update {
                        ProfileUiState(
                            isLoading = false,
                            profileData = response.body.data
                        )
                    }
                }
                is NetworkResponse.UnknownError -> {
                    _profileUiState.update {
                        ProfileUiState(
                            isLoading = false,
                            error = response.error!!.message.toString()
                        )
                    }
                }
                else -> {
                    _profileUiState.update {
                        ProfileUiState(
                            isLoading = false,

                        )
                    }
                }
            }
        }
    }
}