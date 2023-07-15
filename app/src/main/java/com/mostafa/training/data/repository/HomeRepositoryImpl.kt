package com.mostafa.training.data.repository

import com.mostafa.training.data.remote.ApiServices
import com.mostafa.training.data.remote.dto.BannersDTO
import com.mostafa.training.data.remote.dto.CategoriesDTO
import com.mostafa.training.data.remote.dto.ErrorResponse
import com.mostafa.training.data.remote.dto.NotificationsDTO
import com.mostafa.training.data.remote.dto.ProductsDTO
import com.mostafa.training.data.remote.dto.ProductsDetailDTO
import com.mostafa.training.data.remote.response.NetworkResponse
import com.mostafa.training.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HomeRepositoryImpl(
    private val api: ApiServices,
) : HomeRepository {

    override suspend fun getBanners(): NetworkResponse<BannersDTO, ErrorResponse> = api.getBanners()

    override suspend fun getCategories(): NetworkResponse<CategoriesDTO, ErrorResponse> =
        api.getCategories()


    override suspend fun getProducts(authorization: String): Flow<NetworkResponse<ProductsDTO, ErrorResponse>> = flow {
        emit(NetworkResponse.Loading)
        when (val get = api.getProducts(authorization = authorization)) {
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
    override suspend fun getProductDetail(
        id: Int,
        authorization: String
    ): Flow<NetworkResponse<ProductsDetailDTO, ErrorResponse>> = flow {
        emit(NetworkResponse.Loading)
        when (val get = api.getProductDetail(id = id, authorization = authorization)) {
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

    override suspend fun getCategoryDetail(
        id: Int,
        authorization: String
    ): NetworkResponse<ProductsDTO, ErrorResponse> =
        api.getCategoryDetail(id = id, authorization = authorization)

    override suspend fun getNotifications(authorization: String): NetworkResponse<NotificationsDTO, ErrorResponse> =
        api.getNotifications(authorization = authorization)


}