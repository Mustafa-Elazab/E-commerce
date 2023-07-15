package com.mostafa.training.domain.repository

import com.mostafa.training.data.remote.dto.ErrorResponse
import com.mostafa.training.data.remote.dto.ProfileDTO
import com.mostafa.training.data.remote.response.NetworkResponse

interface ProfileRepository {


    suspend fun getProfile(
        authorization: String,
    ): NetworkResponse<ProfileDTO, ErrorResponse>
}