package com.mostafa.training.data.remote.dto


import com.google.gson.annotations.SerializedName

data class CartUpdateDTO(
    @SerializedName("data")
    val `data`: CartItemUpdateDTO?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: Boolean?
)

data class CartItemUpdateDTO(
    @SerializedName("cart")
    val cart: Cart?,
    @SerializedName("sub_total")
    val subTotal: Double?,
    @SerializedName("total")
    val total: Double?
)
data class Cart(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("product")
    val product: ProductDTO?,
    @SerializedName("quantity")
    val quantity: Int?
)

