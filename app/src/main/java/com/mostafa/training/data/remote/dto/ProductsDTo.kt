package com.mostafa.training.data.remote.dto


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable


@Serializable
data class ProductsDTO(

    val `data`: ProductPaginationDTO,

    val message: String?,

    val status: Boolean?
)




@Serializable
data class ProductPaginationDTO(

    val currentPage: Int,

    val `data`: List<ProductDTO>,

    val firstPageUrl: String,

    val from: Int,

    val lastPage: Int,

    val lastPageUrl: String?,

    val nextPageUrl: Int?,

    val path: String?,

    val perPage: Int?,

    val prevPageUrl: Int?,

    val to: Int?,

    val total: Int?
)

@Serializable
data class ProductsDetailDTO(

    val `data`: ProductDTO?,

    val message: String?,

    val status: Boolean?
)


@Serializable
data class ProductDTO(

    val description: String?,

    val discount: Int?=0,

    val id: Int?,

    val image: String?,

    val images: List<String?>?,

    var inCart: Boolean,

    val inFavorites: Boolean,

    val name: String?,

    val oldPrice: Double=0.0,

    val price: Double?
)
