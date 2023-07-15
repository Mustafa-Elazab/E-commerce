package com.mostafa.training.data.repository

import com.mostafa.training.data.remote.ApiServices
import com.mostafa.training.data.remote.dto.AddRemoveCartItemDTO
import com.mostafa.training.data.remote.dto.CartDTO
import com.mostafa.training.data.remote.dto.CartUpdateDTO
import com.mostafa.training.data.remote.dto.ErrorResponse
import com.mostafa.training.data.remote.response.NetworkResponse
import com.mostafa.training.domain.repository.CartRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CartRepositoryImpl(private val api: ApiServices) : CartRepository {

    override suspend fun addOrRemoveCartItem(
        authorization: String,
        product_id: Int
    ): Flow<NetworkResponse<AddRemoveCartItemDTO, ErrorResponse>> = flow {
        emit(NetworkResponse.Loading)
        when (val get =
            api.addOrRemoveCartItem(authorization = authorization, product_id = product_id)) {
            is NetworkResponse.ApiError -> if (get.code == 500) emit(
                NetworkResponse.ApiError(
                    ErrorResponse(data = "", message = "Internal Server Error", status = false),
                    get.code
                )
            ) else emit(NetworkResponse.ApiError(get.body, get.code))

            NetworkResponse.Loading -> emit(NetworkResponse.Loading)
            is NetworkResponse.NetworkError -> emit(NetworkResponse.NetworkError(get.error))
            is NetworkResponse.Success -> emit(NetworkResponse.Success(get.body))
            is NetworkResponse.UnknownError -> emit(NetworkResponse.UnknownError(get.error))
        }

    }

    override suspend fun getCartItems(authorization: String): Flow<NetworkResponse<CartDTO, ErrorResponse>> =
        flow {
            emit(NetworkResponse.Loading)
            when (val get = api.getCartItems(authorization)) {
                is NetworkResponse.ApiError -> if (get.code == 500) emit(
                    NetworkResponse.ApiError(
                        ErrorResponse(data = "", message = "Internal Server Error", status = false),
                        get.code
                    )
                ) else emit(NetworkResponse.ApiError(get.body, get.code))

                NetworkResponse.Loading -> emit(NetworkResponse.Loading)
                is NetworkResponse.NetworkError -> emit(NetworkResponse.NetworkError(get.error))
                is NetworkResponse.Success -> emit(NetworkResponse.Success(get.body))
                is NetworkResponse.UnknownError -> emit(NetworkResponse.UnknownError(get.error))
            }

        }

    override suspend fun deleteFromCart(
        id: Int,
        authorization: String
    ): Flow<NetworkResponse<CartUpdateDTO, ErrorResponse>> = flow {
        emit(NetworkResponse.Loading)
        when (val get = api.deleteFromCart(id = id, authorization = authorization)) {
            is NetworkResponse.ApiError -> if (get.code == 500) emit(
                NetworkResponse.ApiError(
                    ErrorResponse(data = "", message = "Internal Server Error", status = false),
                    get.code
                )
            ) else emit(NetworkResponse.ApiError(get.body, get.code))

            NetworkResponse.Loading -> emit(NetworkResponse.Loading)
            is NetworkResponse.NetworkError -> emit(NetworkResponse.NetworkError(get.error))
            is NetworkResponse.Success -> emit(NetworkResponse.Success(get.body))
            is NetworkResponse.UnknownError -> emit(NetworkResponse.UnknownError(get.error))
        }

    }

    override suspend fun updateCart(
        id: Int,
        authorization: String,
        quantity: Int
    ): Flow<NetworkResponse<CartUpdateDTO, ErrorResponse>> = flow {
        emit(NetworkResponse.Loading)
        when (val get =
            api.updateCart(id = id, authorization = authorization, quantity = quantity)) {
            is NetworkResponse.ApiError -> if (get.code == 500) emit(
                NetworkResponse.ApiError(
                    ErrorResponse(data = "", message = "Internal Server Error", status = false),
                    get.code
                )
            ) else emit(NetworkResponse.ApiError(get.body, get.code))

            NetworkResponse.Loading -> emit(NetworkResponse.Loading)
            is NetworkResponse.NetworkError -> emit(NetworkResponse.NetworkError(get.error))
            is NetworkResponse.Success -> emit(NetworkResponse.Success(get.body))
            is NetworkResponse.UnknownError -> emit(NetworkResponse.UnknownError(get.error))
        }

    }
}