package com.mostafa.training.domain.usecase

import com.mostafa.training.domain.repository.HomeRepository

class GetCategoryUseCase(private val repository: HomeRepository) {

    suspend fun getCategories() = repository.getCategories()
}