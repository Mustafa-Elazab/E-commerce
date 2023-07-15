package com.mostafa.training.data.repository

import com.mostafa.training.data.remote.ApiServices
import com.mostafa.training.data.remote.dto.ErrorResponse
import com.mostafa.training.data.remote.dto.ProductsDTO
import com.mostafa.training.data.remote.response.NetworkResponse
import com.mostafa.training.domain.repository.SearchRepository

class SearchRepositoryImpl(private val api: ApiServices) : SearchRepository {


    override suspend fun searchProduct(
        authorization: String,
        text: String?
    ): NetworkResponse<ProductsDTO, ErrorResponse> =
        api.searchProduct(authorization = authorization, text = text)


}