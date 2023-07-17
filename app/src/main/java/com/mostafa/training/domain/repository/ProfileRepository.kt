package com.mostafa.training.domain.repository

import com.mostafa.training.data.remote.dto.ContactDTO
import com.mostafa.training.data.remote.dto.ErrorResponse
import com.mostafa.training.data.remote.dto.FaqsDTO
import com.mostafa.training.data.remote.dto.ProfileDTO
import com.mostafa.training.data.remote.response.NetworkResponse
import retrofit2.http.GET

interface ProfileRepository {


    suspend fun getProfile(
        authorization: String,
    ): NetworkResponse<ProfileDTO, ErrorResponse>


    suspend fun getContact(
    ): NetworkResponse<ContactDTO, ErrorResponse>


    suspend fun getFaqs(
    ): NetworkResponse<FaqsDTO, ErrorResponse>
}