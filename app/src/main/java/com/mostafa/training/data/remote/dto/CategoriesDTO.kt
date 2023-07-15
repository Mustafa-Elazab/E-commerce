package com.mostafa.training.data.remote.dto


import kotlinx.serialization.Serializable

@Serializable
data class CategoriesDTO(

    val `data`: CategoriesPaginationDTO?,

    val message: String?,

    val status: Boolean?
)


@Serializable
data class CategoriesPaginationDTO(

    val currentPage: Int?,

    val `data`: List<CategoryDTO>?,

    val firstPageUrl: String?,

    val from: Int?,

    val lastPage: Int?,

    val lastPageUrl: String?,

    val nextPageUrl: Int?,

    val path: String?,

    val perPage: Int?,

    val prevPageUrl: Int?,

    val to: Int?,

    val total: Int?
)


