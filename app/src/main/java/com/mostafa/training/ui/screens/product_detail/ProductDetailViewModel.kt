package com.mostafa.training.ui.screens.product_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mostafa.training.data.remote.response.NetworkResponse
import com.mostafa.training.domain.usecase.GetProductByIdUseCase
import com.mostafa.training.ui.screens.product_detail.uiState.ProductDetailUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductDetailViewModel(
    private val productByIdUseCase: GetProductByIdUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {


    private val args: ProductDetailArgs = ProductDetailArgs(savedStateHandle = savedStateHandle)

    private val _productDetailState = MutableStateFlow(ProductDetailUiState())
    val productDetailState get() = _productDetailState.asStateFlow()

    init {
        loadProductDetail(args.id)
    }

    private fun loadProductDetail(id: Int) {
        viewModelScope.launch {
            _productDetailState.update { it.copy(isLoading = true) }

            productByIdUseCase.getProductDetail(
                id,
                "b676yF4HQTAGtP9bYNM2kjAw3VZ6vd63Ar7dr7jQvhISokVKIK5K3Emr4tiPctOBgBlZhV"
            ).collectLatest { response ->
                when (response) {
                    is NetworkResponse.ApiError -> {
                        _productDetailState.update {
                            ProductDetailUiState(
                                error = response.body.message.toString(), isLoading = false
                            )
                        }
                    }

                    is NetworkResponse.NetworkError -> {
                        _productDetailState.update {
                            ProductDetailUiState(
                                error = response.error.message.toString(), isLoading = false
                            )
                        }
                    }

                    is NetworkResponse.Success -> {
                        _productDetailState.update {
                            ProductDetailUiState(
                                product = response.body, isLoading = false
                            )

                        }

                    }

                    is NetworkResponse.UnknownError -> {
                        _productDetailState.update {
                            ProductDetailUiState(
                                error = response.error!!.message.toString(), isLoading = false
                            )
                        }
                    }

                    else -> {

                    }
                }
            }
        }
    }
}