package com.mostafa.training.domain.usecase

import com.mostafa.training.domain.repository.HomeRepository

class GetCategoriesDetailUseCase(private val repository: HomeRepository) {

    suspend operator fun invoke(id: Int, authorization: String) =
        repository.getCategoryDetail(id = id, authorization = authorization)
}