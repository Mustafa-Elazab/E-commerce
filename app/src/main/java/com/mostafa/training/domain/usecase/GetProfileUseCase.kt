package com.mostafa.training.domain.usecase

import com.mostafa.training.domain.repository.ProfileRepository

class GetProfileUseCase(private val repository: ProfileRepository) {

    suspend operator fun invoke(authorization: String) =
        repository.getProfile(authorization = authorization)
}