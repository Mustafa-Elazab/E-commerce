package com.mostafa.training.data.remote.dto


import com.google.gson.annotations.SerializedName


data class ProductsDTO(

    @SerializedName("data")
    val `data`: ProductPaginationDTO?,
    @SerializedName("message")
    val message: Any?,
    @SerializedName("status")
    val status: Boolean?
)


data class ProductPaginationDTO(

    @SerializedName("current_page")
    val currentPage: Int?,
    @SerializedName("data")
    val `data`: List<ProductDTO>?,
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


data class ProductsDetailDTO(

    @SerializedName("data")
    val `data`: ProductDTO?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: Boolean?
)


data class ProductDTO(
    @SerializedName("description")
    val description: String?,
    @SerializedName("discount")
    val discount: Int? = 0,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("images")
    val images: List<String?>?,
    @SerializedName("in_cart")
    val inCart: Boolean?,
    @SerializedName("in_favorites")
    val inFavorites: Boolean?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("old_price")
    val oldPrice: Double? = 0.0,
    @SerializedName("price")
    val price: Double?


)
