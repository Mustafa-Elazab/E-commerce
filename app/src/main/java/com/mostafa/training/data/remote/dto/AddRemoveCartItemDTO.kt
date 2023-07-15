package com.mostafa.training.data.remote.dto


import com.google.gson.annotations.SerializedName

data class AddRemoveCartItemDTO(
    @SerializedName("data")
    val `data`: AddRemoveCartItemData?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: Boolean?
)

data class AddRemoveCartItemData(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("product")
    val product: ProductDTO?,
    @SerializedName("quantity")
    val quantity: Int?
)