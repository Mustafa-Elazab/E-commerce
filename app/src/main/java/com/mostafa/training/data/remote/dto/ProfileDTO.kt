package com.mostafa.training.data.remote.dto


import com.google.gson.annotations.SerializedName

data class ProfileDTO(
    @SerializedName("data")
    val `data`: ProfileData?,
    @SerializedName("message")
    val message: Any?,
    @SerializedName("status")
    val status: Boolean?
)
data class ProfileData(
    @SerializedName("credit")
    val credit: Double?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("phone")
    val phone: String?,
    @SerializedName("points")
    val points: Int?,
    @SerializedName("token")
    val token: String?
)