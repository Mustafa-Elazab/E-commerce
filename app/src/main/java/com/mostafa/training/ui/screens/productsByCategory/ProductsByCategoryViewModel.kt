package com.mostafa.training.ui.screens.productsByCategory

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mostafa.training.data.remote.response.NetworkResponse
import com.mostafa.training.domain.usecase.GetCategoriesDetailUseCase
import com.mostafa.training.ui.screens.home.ui_state.ProductsUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductsByCategoryViewModel(
    private val getCategoriesDetailUseCase: GetCategoriesDetailUseCase,
    private val savedStateHandle: SavedStateHandle
) :
    ViewModel() {

    private val args: ProductsByCategoryArgs =
        ProductsByCategoryArgs(savedStateHandle = savedStateHandle)


//    val title: String = args.name.toString()

    private val _productsUiState = MutableStateFlow(ProductsUiState())
    val productsUiState = _productsUiState.asStateFlow()

    init {
        loadCategoryProducts(args.id)
    }

    private fun loadCategoryProducts(id: Int) {
        viewModelScope.launch {
            _productsUiState.update { it.copy(isLoading = true) }
            when (val response = getCategoriesDetailUseCase.invoke(
                id = id,
                authorization = "b676yF4HQTAGtP9bYNM2kjAw3VZ6vd63Ar7dr7jQvhISokVKIK5K3Emr4tiPctOBgBlZhV"
            )){
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
                            products = response.body.data!!.data, isLoading = false
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
                else ->{

                }
            }
        }
    }
}