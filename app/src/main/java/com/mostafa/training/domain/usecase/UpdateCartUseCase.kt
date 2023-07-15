package com.mostafa.training.domain.usecase

import com.mostafa.training.domain.repository.CartRepository

class UpdateCartUseCase(private val repository: CartRepository) {

    suspend operator fun invoke(id: Int, authorization: String, quantity: Int) =
        repository.updateCart(id = id, authorization = authorization, quantity = quantity)
}