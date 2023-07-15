package com.mostafa.training.data.remote.dto


import com.google.gson.annotations.SerializedName

data class CartDTO(
    @SerializedName("data")
    val `data`: CartDataDTO?,
    @SerializedName("message")
    val message: Any?,
    @SerializedName("status")
    val status: Boolean?
)

data class CartDataDTO(
    @SerializedName("cart_items")
    val cartItems: List<CartItemDTO?>?,
    @SerializedName("sub_total")
    val subTotal: Double?,
    @SerializedName("total")
    val total: Double?
)

data class CartItemDTO(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("product")
    val product: ProductDTO?,
    @SerializedName("quantity")
    var quantity: Int?
)

