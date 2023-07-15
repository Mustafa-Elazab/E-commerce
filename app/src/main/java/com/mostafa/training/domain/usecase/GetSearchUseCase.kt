package com.mostafa.training.domain.usecase

import com.mostafa.training.domain.repository.SearchRepository

class GetSearchUseCase(private val repository: SearchRepository) {

    suspend operator fun invoke(authorization: String, text: String) =
        repository.searchProduct(authorization = authorization, text = text)
}