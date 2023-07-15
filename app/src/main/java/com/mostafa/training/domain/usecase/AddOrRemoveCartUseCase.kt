package com.mostafa.training.domain.usecase

import com.mostafa.training.domain.repository.CartRepository

class AddOrRemoveCartUseCase(private val repository: CartRepository) {

    suspend operator fun invoke(authorization: String, product_id: Int) =
        repository.addOrRemoveCartItem(authorization = authorization, product_id = product_id)

}