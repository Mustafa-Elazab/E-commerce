package com.mostafa.training.ui.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mostafa.training.data.remote.response.NetworkResponse
import com.mostafa.training.domain.usecase.GetBannerUseCase
import com.mostafa.training.domain.usecase.GetCategoryUseCase
import com.mostafa.training.domain.usecase.GetProductsUseCase
import com.mostafa.training.ui.screens.home.ui_state.BannerUiState
import com.mostafa.training.ui.screens.home.ui_state.CategoryUiState
import com.mostafa.training.ui.screens.home.ui_state.ProductsUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val bannerUseCase: GetBannerUseCase,
    private val categoryUseCase: GetCategoryUseCase,
    private val productsUseCase: GetProductsUseCase,

    ) : ViewModel() {

    private val _bannersUiState = MutableStateFlow(BannerUiState())
    val bannersUiState get() = _bannersUiState.asStateFlow()

    private val _categoriesUiState = MutableStateFlow(CategoryUiState())
    val categoriesUiState = _categoriesUiState.asStateFlow()

    private val _productsUiState = MutableStateFlow(ProductsUiState())
    val productsUiState = _productsUiState.asStateFlow()


    val authorization = "NN8p3D6X3dQL0GllyvSSQwY4J4v2fDQ8wIdQWDrnVGNoYrDMpkdVuLgEN6HULhotByHqjK"

    init {
        loadBanners()
        loadCategories()
        loadProducts()
    }


    private fun loadProducts() {
        viewModelScope.launch {
            _productsUiState.update { it.copy(isLoading = true) }

            productsUseCase.getProducts(authorization=authorization).collectLatest {response->
                when(response){
                    is NetworkResponse.ApiError -> {
                        _productsUiState.update {
                            ProductsUiState(
                                error = response.body.message.toString(), isLoading = false
                            )
                        }
                    }

                    is NetworkResponse.NetworkError -> {
                        _productsUiState.update {
                            ProductsUiState(
                                error = response.error.message.toString(), isLoading = false
                            )
                        }
                    }

                    is NetworkResponse.Success -> {
                        _productsUiState.update {
                            ProductsUiState(
                                products = response.body.data.data, isLoading = false
                            )
                        }
                    }

                    is NetworkResponse.UnknownError -> {
                        _productsUiState.update {
                            ProductsUiState(
                                error = response.error!!.message.toString(), isLoading = false
                            )
                        }
                    }

                    else -> {
                        _productsUiState.update { it.copy(isLoading = false) }
                    }
                }
            }

        }


    }




    private fun loadCategories() {
        viewModelScope.launch {
            _categoriesUiState.update { it.copy(isLoading = true) }
            when (val response = categoryUseCase.getCategories()) {
                is NetworkResponse.ApiError -> {
                    _categoriesUiState.update {
                        CategoryUiState(
                            error = response.body.message.toString(),
                            isLoading = false
                        )
                    }
                }

                is NetworkResponse.NetworkError -> {
                    _categoriesUiState.update {
                        CategoryUiState(
                            error = response.error.message.toString(),
                            isLoading = false
                        )
                    }
                }

                is NetworkResponse.Success -> {
                    _categoriesUiState.update {
                        CategoryUiState(
                            categories = response.body.data!!.data,
                            isLoading = false
                        )
                    }
                }

                is NetworkResponse.UnknownError -> {
                    _categoriesUiState.update {
                        CategoryUiState(
                            error = response.error!!.message.toString(),
                            isLoading = false
                        )
                    }
                }

                else -> {
                    _categoriesUiState.update { it.copy(isLoading = false) }
                }
            }
        }
    }


    private fun loadBanners() {
        viewModelScope.launch {
            _bannersUiState.update { it.copy(isLoading = true) }
            when (val response = bannerUseCase.getBanners()) {
                is NetworkResponse.ApiError -> {
                    _bannersUiState.update {
                        BannerUiState(
                            error = response.body.message.toString(),
                            isLoading = false
                        )
                    }
                }

                is NetworkResponse.NetworkError -> {
                    _bannersUiState.update {
                        BannerUiState(
                            error = response.error.message.toString(),
                            isLoading = false
                        )
                    }
                }

                is NetworkResponse.Success -> {
                    _bannersUiState.update {
                        BannerUiState(
                            banners = response.body.data,
                            isLoading = false
                        )
                    }
                }

                is NetworkResponse.UnknownError -> {
                    _bannersUiState.update {
                        BannerUiState(
                            error = response.error!!.message.toString(),
                            isLoading = false
                        )
                    }
                }

                else -> {
                    _bannersUiState.update { it.copy(isLoading = false) }
                }
            }
        }
    }
}