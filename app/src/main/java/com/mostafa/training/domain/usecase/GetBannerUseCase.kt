package com.mostafa.training.domain.usecase

import com.mostafa.training.domain.repository.HomeRepository

class GetBannerUseCase (private val repository: HomeRepository){

    suspend fun getBanners() = repository.getBanners()
}