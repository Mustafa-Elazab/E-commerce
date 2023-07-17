package com.mostafa.training.domain.repository

import com.mostafa.training.data.remote.dto.AddOrRemoveFavoritesDTO
import com.mostafa.training.data.remote.dto.ErrorResponse
import com.mostafa.training.data.remote.dto.FavoritesDTO
import com.mostafa.training.data.remote.response.NetworkResponse
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {

    suspend fun addOrRemoveFavoriteItem(
        authorization: String,
        product_id: Int
    ): Flow<NetworkResponse<AddOrRemoveFavoritesDTO, ErrorResponse>>


    suspend fun getFavoritesItems(
        authorization: String,
    ): Flow<NetworkResponse<FavoritesDTO, ErrorResponse>>
}