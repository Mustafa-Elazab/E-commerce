package com.mostafa.training.domain.usecase

import com.mostafa.training.domain.repository.HomeRepository

class GetNotificationsUseCase (private val repository: HomeRepository) {

    suspend operator fun invoke(authorization: String) =
        repository.getNotifications(authorization = authorization)
}