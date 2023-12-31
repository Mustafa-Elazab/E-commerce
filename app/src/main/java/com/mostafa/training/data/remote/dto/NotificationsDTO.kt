package com.mostafa.training.data.remote.dto


import com.google.gson.annotations.SerializedName

data class NotificationsDTO(
    @SerializedName("data")
    val `data`: NotificationsPaginationDTO?,
    @SerializedName("message")
    val message: Any?,
    @SerializedName("status")
    val status: Boolean?
)

data class NotificationsPaginationDTO(
    @SerializedName("current_page")
    val currentPage: Int?,
    @SerializedName("data")
    val `data`: List<NotificationDTO>?,
    @SerializedName("first_page_url")
    val firstPageUrl: String?,
    @SerializedName("from")
    val from: Int?,
    @SerializedName("last_page")
    val lastPage: Int?,
    @SerializedName("last_page_url")
    val lastPageUrl: String?,
    @SerializedName("next_page_url")
    val nextPageUrl: Int?,
    @SerializedName("path")
    val path: String?,
    @SerializedName("per_page")
    val perPage: Int?,
    @SerializedName("prev_page_url")
    val prevPageUrl: Int?,
    @SerializedName("to")
    val to: Int?,
    @SerializedName("total")
    val total: Int?
)
data class NotificationDTO(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("title")
    val title: String?
)