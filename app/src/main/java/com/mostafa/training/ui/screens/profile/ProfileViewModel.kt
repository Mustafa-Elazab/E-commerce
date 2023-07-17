package com.mostafa.training.ui.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mostafa.training.data.remote.response.NetworkResponse
import com.mostafa.training.domain.usecase.GetContactUseCase
import com.mostafa.training.domain.usecase.GetFaqsUseCase
import com.mostafa.training.domain.usecase.GetProfileUseCase
import com.mostafa.training.ui.screens.contact.uiState.ContactUiState
import com.mostafa.training.ui.screens.faqs.uiState.FaqsUiState
import com.mostafa.training.ui.screens.profile.uiState.ProfileUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val profileUseCase: GetProfileUseCase, private val contactUseCase: GetContactUseCase,
    private val faqsUseCase: GetFaqsUseCase
) : ViewModel() {


    private val _profileUiState = MutableStateFlow(ProfileUiState())
    val profileUiState get() = _profileUiState.asStateFlow()

    private val _faqsUiState = MutableStateFlow(FaqsUiState())
    val faqsUiState get() = _faqsUiState.asStateFlow()
    private val _contactUiState = MutableStateFlow(ContactUiState())
    val contactUiState get() = _contactUiState.asStateFlow()

    init {
        loadProfile()
    }

     fun loadProfile() {

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

                is NetworkResponse.NetworkError -> {
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

    fun getFaqs() {
        viewModelScope.launch {
            _faqsUiState.update { it.copy(isLoading = true) }
            when (val response = faqsUseCase.invoke()) {

                is NetworkResponse.ApiError -> {
                    _faqsUiState.update {
                        FaqsUiState(
                            isLoading = false,
                            error = response.body.message.toString()
                        )
                    }
                }

                is NetworkResponse.NetworkError -> {
                    _faqsUiState.update {
                        FaqsUiState(
                            isLoading = false,
                            error = response.error.message.toString()
                        )
                    }
                }

                is NetworkResponse.Success -> {
                    _faqsUiState.update {
                        FaqsUiState(
                            isLoading = false,
                            data = response.body
                        )
                    }
                }

                is NetworkResponse.UnknownError -> {
                    _faqsUiState.update {
                        FaqsUiState(
                            isLoading = false,
                            error = response.error!!.message.toString()
                        )
                    }
                }

                else -> {
                    _faqsUiState.update {
                        FaqsUiState(
                            isLoading = false,
                            )
                    }
                }
            }
        }
    }

    fun getContact() {
        viewModelScope.launch {
            _contactUiState.update { it.copy(isLoading = true) }
            when (val response = contactUseCase.invoke()) {

                is NetworkResponse.ApiError -> {
                    _contactUiState.update {
                        ContactUiState(
                            isLoading = false,
                            error = response.body.message.toString()
                        )
                    }
                }

                is NetworkResponse.NetworkError -> {
                    _contactUiState.update {
                        ContactUiState(
                            isLoading = false,
                            error = response.error.message.toString()
                        )
                    }
                }

                is NetworkResponse.Success -> {
                    _contactUiState.update {
                        ContactUiState(
                            isLoading = false,
                            data = response.body
                        )
                    }
                }

                is NetworkResponse.UnknownError -> {
                    _contactUiState.update {
                        ContactUiState(
                            isLoading = false,
                            error = response.error!!.message.toString()
                        )
                    }
                }

                else -> {
                    _contactUiState.update {
                        ContactUiState(
                            isLoading = false,
                        )
                    }
                }
            }
        }
    }
}