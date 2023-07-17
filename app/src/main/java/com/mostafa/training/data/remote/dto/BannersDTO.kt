package com.mostafa.training.data.remote.dto


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable


data class BannersDTO(
    @SerializedName("data")
    val `data`: List<BannerDTO?>?,
    @SerializedName("message")
    val message: Any?,
    @SerializedName("status")
    val status: Boolean?
)

@Serializable
data class CategoryDTO(

    val id: Int?,

    val image: String?,

    val name: String?
)


data class BannerDTO(
    @SerializedName("category")
    val category: CategoryDTO?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("product")
    val product: ProductDTO?
)