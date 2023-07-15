package com.mostafa.training.ui.screens.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mostafa.training.data.remote.response.NetworkResponse
import com.mostafa.training.domain.usecase.GetCategoryUseCase
import com.mostafa.training.ui.screens.home.ui_state.CategoryUiState
import com.mostafa.training.ui.screens.home.ui_state.ProductsUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CategoryViewModel(private val categoryUseCase: GetCategoryUseCase) : ViewModel() {

    private val _productsUiState = MutableStateFlow(ProductsUiState())
    val productsUiState = _productsUiState.asStateFlow()

    private val _categoriesUiState = MutableStateFlow(CategoryUiState())
    val categoriesUiState = _categoriesUiState.asStateFlow()

    //private val args: ProductDetailArgs = ProductDetailArgs(savedStateHandle = savedStateHandle)

    init {
        loadCategories()
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

                }
            }
        }
    }
}