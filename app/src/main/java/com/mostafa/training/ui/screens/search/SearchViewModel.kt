package com.mostafa.training.ui.screens.search

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mostafa.training.data.remote.response.NetworkResponse
import com.mostafa.training.domain.usecase.GetSearchUseCase
import com.mostafa.training.ui.screens.home.ui_state.ProductsUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel(private val searchUseCase: GetSearchUseCase) : ViewModel() {


    private val _productsUiState = MutableStateFlow(ProductsUiState())
    val productsUiState = _productsUiState.asStateFlow()

    val searchQuery = mutableStateOf("")

    fun setSearchQuery(query: String) {
        viewModelScope.launch {
            delay(200)
            searchForProduct(text = query)
        }
        searchQuery.value = query


    }

    private fun searchForProduct(text: String) {

        viewModelScope.launch {

            _productsUiState.update { it.copy(isLoading = true) }


            when (val response = searchUseCase.invoke(
                authorization = "buMt55pRam46AfHHC00O7RqyrnrZ6vMiEEs3gjTB3Pw80CU6d7O11TOfPmOzd4EjfhmFyH",
                text = text
            )) {
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
                    val products = response.body.data?.data ?: emptyList()
                    _productsUiState.update {
                        ProductsUiState(
                            products = products, isLoading = false
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

                else -> {}
            }


        }

    }


}