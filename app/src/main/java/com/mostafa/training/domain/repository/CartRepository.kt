package com.mostafa.training.domain.repository

import com.mostafa.training.data.remote.dto.AddRemoveCartItemDTO
import com.mostafa.training.data.remote.dto.CartDTO
import com.mostafa.training.data.remote.dto.CartUpdateDTO
import com.mostafa.training.data.remote.dto.ErrorResponse
import com.mostafa.training.data.remote.response.NetworkResponse
import kotlinx.coroutines.flow.Flow

interface CartRepository {


    suspend fun addOrRemoveCartItem(
        authorization: String,
        product_id: Int
    ): Flow<NetworkResponse<AddRemoveCartItemDTO, ErrorResponse>>


    suspend fun getCartItems(
        authorization: String,
    ): Flow<NetworkResponse<CartDTO, ErrorResponse>>


    suspend fun deleteFromCart(
        id: Int,
        authorization: String
    ): Flow<NetworkResponse<CartUpdateDTO, ErrorResponse>>


    suspend fun updateCart(
        id: Int,
        authorization: String,
        quantity: Int
    ): Flow<NetworkResponse<CartUpdateDTO, ErrorResponse>>
}