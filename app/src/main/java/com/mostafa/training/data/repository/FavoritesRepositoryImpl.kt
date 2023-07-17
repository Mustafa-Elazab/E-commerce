package com.mostafa.training.data.repository

import com.mostafa.training.data.remote.ApiServices
import com.mostafa.training.data.remote.dto.AddOrRemoveFavoritesDTO
import com.mostafa.training.data.remote.dto.ErrorResponse
import com.mostafa.training.data.remote.dto.FavoritesDTO
import com.mostafa.training.data.remote.response.NetworkResponse
import com.mostafa.training.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FavoritesRepositoryImpl(private val api: ApiServices) : FavoritesRepository {


    override suspend fun addOrRemoveFavoriteItem(
        authorization: String,
        product_id: Int
    ): Flow<NetworkResponse<AddOrRemoveFavoritesDTO, ErrorResponse>> = flow {
        emit(NetworkResponse.Loading)
        when (val get =
            api.addOrRemoveFavoriteItem(authorization = authorization, product_id = product_id)) {
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

    override suspend fun getFavoritesItems(authorization: String): Flow<NetworkResponse<FavoritesDTO, ErrorResponse>> =
        flow {
            emit(NetworkResponse.Loading)
            when (val get =
                api.getFavoritesItems(authorization = authorization)) {
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