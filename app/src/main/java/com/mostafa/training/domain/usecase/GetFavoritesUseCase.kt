package com.mostafa.training.domain.usecase

import com.mostafa.training.domain.repository.FavoritesRepository

class GetFavoritesUseCase(private val repository: FavoritesRepository) {


    suspend operator fun invoke(
        authorization: String
    ) = repository.getFavoritesItems(authorization = authorization)


}