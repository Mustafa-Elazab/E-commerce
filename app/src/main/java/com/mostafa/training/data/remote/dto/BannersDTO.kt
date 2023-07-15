package com.mostafa.training.data.remote.dto


import kotlinx.serialization.Serializable

@Serializable
data class BannersDTO(

    val `data`: List<BannerDTO?>?,

    val message: String?,

    val status: Boolean?
)

@Serializable
data class CategoryDTO(

    val id: Int?,

    val image: String?,

    val name: String?
)

@Serializable
data class BannerDTO(

    val category: CategoryDTO?,

    val id: Int?,

    val image: String?,

    val product: ProductDTO?
)