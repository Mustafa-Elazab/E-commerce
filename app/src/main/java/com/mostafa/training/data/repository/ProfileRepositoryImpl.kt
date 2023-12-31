package com.mostafa.training.data.repository

import com.mostafa.training.data.remote.ApiServices
import com.mostafa.training.data.remote.dto.ErrorResponse
import com.mostafa.training.data.remote.dto.ProfileDTO
import com.mostafa.training.data.remote.response.NetworkResponse
import com.mostafa.training.domain.repository.ProfileRepository

class ProfileRepositoryImpl(private val api: ApiServices) : ProfileRepository {


    override suspend fun getProfile(authorization: String): NetworkResponse<ProfileDTO, ErrorResponse> =
        api.getProfile(authorization)

}