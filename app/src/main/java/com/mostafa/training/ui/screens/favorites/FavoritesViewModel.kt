package com.mostafa.training.ui.screens.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mostafa.training.data.remote.response.NetworkResponse
import com.mostafa.training.domain.usecase.AddOrRemoveFavoritesUseCase
import com.mostafa.training.domain.usecase.GetFavoritesUseCase
import com.mostafa.training.ui.screens.favorites.uiState.AddOrRemoveFavoritesUiState
import com.mostafa.training.ui.screens.favorites.uiState.FavoritesUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val favoritesUseCase: GetFavoritesUseCase,
    private val addOrRemoveFavoritesUseCase: AddOrRemoveFavoritesUseCase
) : ViewModel() {


    private val _favoritesUiState = MutableStateFlow(FavoritesUiState())
    val favoritesUiState get() = _favoritesUiState.asStateFlow()

    private val _addOrRemoveUiState = MutableStateFlow(AddOrRemoveFavoritesUiState())
    val addOrRemoveUiState get() = _addOrRemoveUiState.asStateFlow()

    fun getAllFavoritesProducts() {
        viewModelScope.launch {
            favoritesUseCase.invoke(authorization = "NN8p3D6X3dQL0GllyvSSQwY4J4v2fDQ8wIdQWDrnVGNoYrDMpkdVuLgEN6HULhotByHqjK")
                .collectLatest { response ->
                    when (response) {
                        is NetworkResponse.ApiError -> {
                            _favoritesUiState.update {
                                FavoritesUiState(
                                    error = response.body.message.toString(), isLoading = false
                                )
                            }
                        }

                        is NetworkResponse.NetworkError -> {
                            _favoritesUiState.update {
                                FavoritesUiState(
                                    error = response.error.message.toString(), isLoading = false
                                )
                            }
                        }

                        is NetworkResponse.Success -> {

                            _favoritesUiState.update {
                                FavoritesUiState(
                                    data = response.body, isLoading = false
                                )
                            }

                        }

                        is NetworkResponse.UnknownError -> {
                            _favoritesUiState.update {
                                FavoritesUiState(
                                    error = response.error!!.message.toString(), isLoading = false
                                )
                            }
                        }

                        else
                        -> {
                            _favoritesUiState.update { it.copy(isLoading = false) }
                        }
                    }
                }
        }
    }


    fun addOrRemoveFavorites(product_id: Int) {
        viewModelScope.launch {
            addOrRemoveFavoritesUseCase.invoke(
                authorization = "NN8p3D6X3dQL0GllyvSSQwY4J4v2fDQ8wIdQWDrnVGNoYrDMpkdVuLgEN6HULhotByHqjK",
                product_id = product_id
            )
                .collectLatest { response ->
                    when (response) {
                        is NetworkResponse.ApiError -> {
                            _addOrRemoveUiState.update {
                                AddOrRemoveFavoritesUiState(
                                    error = response.body.message.toString(), isLoading = false
                                )
                            }
                        }

                        is NetworkResponse.NetworkError -> {
                            _addOrRemoveUiState.update {
                                AddOrRemoveFavoritesUiState(
                                    error = response.error.message.toString(), isLoading = false
                                )
                            }
                        }

                        is NetworkResponse.Success -> {
                            getAllFavoritesProducts()
                            _addOrRemoveUiState.update {
                                AddOrRemoveFavoritesUiState(
                                    addOrRemoveFavoritesDTO = response.body, isLoading = false
                                )
                            }

                        }

                        is NetworkResponse.UnknownError -> {
                            _addOrRemoveUiState.update {
                                AddOrRemoveFavoritesUiState(
                                    error = response.error!!.message.toString(), isLoading = false
                                )
                            }
                        }

                        else
                        -> {
                            _addOrRemoveUiState.update { it.copy(isLoading = false) }
                        }
                    }
                }
        }
    }
}