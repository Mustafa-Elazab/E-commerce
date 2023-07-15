package com.mostafa.training.domain.usecase

import com.mostafa.training.domain.repository.HomeRepository

class GetProductsUseCase(private val repository: HomeRepository) {




    suspend fun getProducts(
        authorization: String
    ) = repository.getProducts(authorization = authorization)


}