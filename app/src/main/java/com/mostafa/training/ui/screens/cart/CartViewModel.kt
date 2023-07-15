package com.mostafa.training.ui.screens.cart

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mostafa.training.data.remote.dto.CartItemDTO
import com.mostafa.training.data.remote.response.NetworkResponse
import com.mostafa.training.domain.usecase.AddOrRemoveCartUseCase
import com.mostafa.training.domain.usecase.GetCartItemsUseCase
import com.mostafa.training.domain.usecase.UpdateCartUseCase
import com.mostafa.training.ui.screens.cart.uiState.AddOrRemoveUiState
import com.mostafa.training.ui.screens.cart.uiState.CartUiState
import com.mostafa.training.ui.screens.cart.uiState.UpdateCartUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CartViewModel(
    private val cartItemsUseCase: GetCartItemsUseCase,
    private val updateCartUseCase: UpdateCartUseCase,
    private val addOrRemoveCartUseCase: AddOrRemoveCartUseCase
) : ViewModel() {

    private val _cartUiState = MutableStateFlow(CartUiState())
    val cartUiState get() = _cartUiState.asStateFlow()

    private val _addOrRemoveUiState = MutableStateFlow(AddOrRemoveUiState())
    val addOrRemoveUiState get() = _addOrRemoveUiState.asStateFlow()

    private val _updateCartUiState = MutableStateFlow(UpdateCartUiState())
    val updateCartUiState get() = _updateCartUiState.asStateFlow()

    private val cartItemQuantities = mutableStateMapOf<Int, Int>()

    private val _subTotal = mutableStateOf(0.0)
    val subTotal: State<Double> = _subTotal

    private val _total = mutableStateOf(0.0)
    val total: State<Double> = _total


    init {
        loadingProductsInCart()
    }


    private fun loadingProductsInCart() {
        viewModelScope.launch {
            _cartUiState.update { it.copy(isLoading = true) }
            cartItemsUseCase.invoke(authorization = "NN8p3D6X3dQL0GllyvSSQwY4J4v2fDQ8wIdQWDrnVGNoYrDMpkdVuLgEN6HULhotByHqjK")
                .collectLatest { response ->
                    when (response) {
                        is NetworkResponse.ApiError -> {
                            _cartUiState.update {
                                CartUiState(
                                    error = response.body.message.toString(), isLoading = false
                                )
                            }
                        }

                        is NetworkResponse.NetworkError -> {
                            _cartUiState.update {
                                CartUiState(
                                    error = response.error.message.toString(), isLoading = false
                                )
                            }
                        }

                        is NetworkResponse.Success -> {
                            updateCartItemQuantities(response.body.data?.cartItems)
                            _cartUiState.update {
                                CartUiState(
                                    cartData = response.body.data, isLoading = false
                                )
                            }
                        }

                        is NetworkResponse.UnknownError -> {
                            _cartUiState.update {
                                CartUiState(
                                    error = response.error!!.message.toString(), isLoading = false
                                )
                            }
                        }

                        else
                        -> {
                            _cartUiState.update { it.copy(isLoading = false) }
                        }
                    }
                }

        }

    }

    fun updateCartItemQuantities(cartItems: List<CartItemDTO?>?) {
        cartItems?.forEach { cartItem ->
            cartItem?.let {
                cartItemQuantities[it.id!!] = it.quantity!!
            }
        }
    }


    fun getCartItemQuantity(cartItemId: Int): Int {
        return cartItemQuantities[cartItemId] ?: 0
    }


    private fun updateCartItemQuantityInMap(cartItemId: Int, quantity: Int) {
        cartItemQuantities[cartItemId] = quantity
    }


    fun updateCartItemQuantity(quantity: Int, cartId: Int) {
        viewModelScope.launch {
            _updateCartUiState.update { it.copy(isLoading = true) }
            updateCartUseCase.invoke(
                id = cartId,
                authorization = "NN8p3D6X3dQL0GllyvSSQwY4J4v2fDQ8wIdQWDrnVGNoYrDMpkdVuLgEN6HULhotByHqjK",
                quantity = quantity
            ).collectLatest { response ->

                when (response) {
                    is NetworkResponse.ApiError -> {
                        _updateCartUiState.update {
                            UpdateCartUiState(
                                error = response.body.message.toString(), isLoading = false
                            )
                        }
                    }

                    is NetworkResponse.NetworkError -> {
                        _updateCartUiState.update {
                            UpdateCartUiState(
                                error = response.error.message.toString(), isLoading = false
                            )
                        }
                    }

                    is NetworkResponse.Success -> {

                        updateCartItemQuantityInMap(cartId, quantity)
                        _updateCartUiState.update {
                            UpdateCartUiState(
                                cartUpdateData = response.body.data, isLoading = false
                            )
                        }
                    }

                    is NetworkResponse.UnknownError -> {
                        _updateCartUiState.update {
                            UpdateCartUiState(
                                error = response.error!!.message.toString(), isLoading = false
                            )
                        }
                    }

                    else -> {
                        _updateCartUiState.update { it.copy(isLoading = false) }
                    }
                }
            }
        }
    }


    fun addOrRemoveItemFromCart(product_id: Int) {
        viewModelScope.launch {
            _addOrRemoveUiState.update { it.copy(isLoading = true) }

            addOrRemoveCartUseCase.invoke(
                authorization = "NN8p3D6X3dQL0GllyvSSQwY4J4v2fDQ8wIdQWDrnVGNoYrDMpkdVuLgEN6HULhotByHqjK",
                product_id = product_id
            ).collectLatest { response ->
                when (response) {
                    is NetworkResponse.ApiError -> {
                        _addOrRemoveUiState.update {
                            AddOrRemoveUiState(
                                error = response.body.message.toString(), isLoading = false
                            )
                        }
                    }

                    is NetworkResponse.NetworkError -> {
                        _addOrRemoveUiState.update {
                            AddOrRemoveUiState(
                                error = response.error.message.toString(), isLoading = false
                            )
                        }
                    }

                    is NetworkResponse.Success -> {
                        _addOrRemoveUiState.update {
                            AddOrRemoveUiState(
                                addRemoveCartItemDTO = response.body, isLoading = false
                            )
                        }
                    }

                    is NetworkResponse.UnknownError -> {
                        _addOrRemoveUiState.update {
                            AddOrRemoveUiState(
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