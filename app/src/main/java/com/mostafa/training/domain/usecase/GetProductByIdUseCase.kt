package com.mostafa.training.domain.usecase

import com.mostafa.training.domain.repository.HomeRepository

class GetProductByIdUseCase(private val repository: HomeRepository) {


    suspend fun getProductDetail(
        id: Int,
        authorization: String
    ) = repository.getProductDetail(id = id, authorization = authorization)
}