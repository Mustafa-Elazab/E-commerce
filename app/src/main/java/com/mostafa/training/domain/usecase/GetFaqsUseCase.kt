package com.mostafa.training.domain.usecase

import com.mostafa.training.domain.repository.ProfileRepository

class GetFaqsUseCase(private val repository: ProfileRepository) {
    suspend operator fun invoke() = repository.getFaqs()

}