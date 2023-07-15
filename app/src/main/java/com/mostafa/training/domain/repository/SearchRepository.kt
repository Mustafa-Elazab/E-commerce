package com.mostafa.training.domain.repository

import com.mostafa.training.data.remote.dto.ErrorResponse
import com.mostafa.training.data.remote.dto.ProductsDTO

import com.mostafa.training.data.remote.response.NetworkResponse
import kotlinx.coroutines.flow.Flow

interface SearchRepository {


    suspend fun searchProduct(
        authorization: String,
        text: String?,
    ): NetworkResponse<ProductsDTO, ErrorResponse>
}