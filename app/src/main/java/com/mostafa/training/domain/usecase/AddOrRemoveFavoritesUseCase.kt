package com.mostafa.training.domain.usecase

import com.mostafa.training.domain.repository.FavoritesRepository

class AddOrRemoveFavoritesUseCase(private val repository: FavoritesRepository) {


    suspend operator fun invoke(
        authorization: String,
        product_id: Int
    ) = repository.addOrRemoveFavoriteItem(authorization = authorization, product_id = product_id)


}