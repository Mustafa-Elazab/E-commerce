package com.mostafa.training.domain.usecase

import com.mostafa.training.domain.repository.CartRepository
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow

class GetCartItemsUseCase(private val repository: CartRepository) {

    operator fun invoke(authorization: String) = flow {
        emitAll(repository.getCartItems(authorization = authorization))
    }

}