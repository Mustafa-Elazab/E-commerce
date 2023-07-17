package com.mostafa.training.data.remote.dto


import com.google.gson.annotations.SerializedName

data class AddOrRemoveFavoritesDTO(
    @SerializedName("data")
    val `data`: FavoritesData?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: Boolean?
)

data class FavoritesData(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("product")
    val product: FavoritesProduct?
)

data class FavoritesProduct(
    @SerializedName("discount")
    val discount: Int?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("old_price")
    val oldPrice: Int?,
    @SerializedName("price")
    val price: Int?
)