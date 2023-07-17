package com.mostafa.training.data.remote.dto


import com.google.gson.annotations.SerializedName

data class FavoritesDTO(
    @SerializedName("data")
    val `data`: FavoritesPaginationDTO?,
    @SerializedName("message")
    val message: Any?,
    @SerializedName("status")
    val status: Boolean?
)
data class FavoritesPaginationDTO(
    @SerializedName("current_page")
    val currentPage: Int?,
    @SerializedName("data")
    val `data`: List<FavoriteDTO>?,
    @SerializedName("first_page_url")
    val firstPageUrl: String?,
    @SerializedName("from")
    val from: Int?,
    @SerializedName("last_page")
    val lastPage: Int?,
    @SerializedName("last_page_url")
    val lastPageUrl: String?,
    @SerializedName("next_page_url")
    val nextPageUrl: Any?,
    @SerializedName("path")
    val path: String?,
    @SerializedName("per_page")
    val perPage: Int?,
    @SerializedName("prev_page_url")
    val prevPageUrl: Any?,
    @SerializedName("to")
    val to: Int?,
    @SerializedName("total")
    val total: Int?
)

data class FavoriteDTO(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("product")
    val product: ProductDTO?
)
