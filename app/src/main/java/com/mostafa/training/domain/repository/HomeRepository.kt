package com.mostafa.training.domain.repository

import com.mostafa.training.data.remote.dto.BannersDTO
import com.mostafa.training.data.remote.dto.CategoriesDTO
import com.mostafa.training.data.remote.dto.ErrorResponse
import com.mostafa.training.data.remote.dto.NotificationsDTO
import com.mostafa.training.data.remote.dto.ProductsDTO
import com.mostafa.training.data.remote.dto.ProductsDetailDTO
import com.mostafa.training.data.remote.response.NetworkResponse
import kotlinx.coroutines.flow.Flow

interface HomeRepository {


    suspend fun getBanners(): NetworkResponse<BannersDTO, ErrorResponse>


    suspend fun getCategories(): NetworkResponse<CategoriesDTO, ErrorResponse>


    suspend fun getProducts(
        authorization: String
    ): Flow<NetworkResponse<ProductsDTO, ErrorResponse>>


    suspend fun getProductDetail(
        id: Int,
        authorization: String
    ): Flow<NetworkResponse<ProductsDetailDTO, ErrorResponse>>


    suspend fun getCategoryDetail(
        id: Int,
        authorization: String
    ): NetworkResponse<ProductsDTO, ErrorResponse>


    suspend fun getNotifications(
        authorization: String
    ): NetworkResponse<NotificationsDTO, ErrorResponse>

}