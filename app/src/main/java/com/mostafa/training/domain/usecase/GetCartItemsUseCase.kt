package com.mostafa.training.domain.usecase

import com.mostafa.training.domain.repository.CartRepository

class GetCartItemsUseCase(private val repository: CartRepository) {

    suspend operator fun invoke(authorization: String) =
        repository.getCartItems(authorization = authorization)
}