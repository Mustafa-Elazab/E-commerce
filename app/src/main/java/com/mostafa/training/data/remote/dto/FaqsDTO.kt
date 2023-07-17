package com.mostafa.training.data.remote.dto


import com.google.gson.annotations.SerializedName

data class FaqsDTO(
    @SerializedName("data")
    val `data`: FaqsPaginationDTO?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: Boolean?
)

data class FaqsPaginationDTO(
    @SerializedName("current_page")
    val currentPage: Int?,
    @SerializedName("data")
    val `data`: List<FaqsData>?,
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

data class FaqsData(
    @SerializedName("answer")
    val answer: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("question")
    val question: String?
)