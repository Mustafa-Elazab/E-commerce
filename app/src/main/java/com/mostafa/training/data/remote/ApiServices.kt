package com.mostafa.training.data.remote

import com.mostafa.training.data.remote.dto.AddOrRemoveFavoritesDTO
import com.mostafa.training.data.remote.dto.AddRemoveCartItemDTO
import com.mostafa.training.data.remote.dto.BannersDTO
import com.mostafa.training.data.remote.dto.CartDTO
import com.mostafa.training.data.remote.dto.CartUpdateDTO
import com.mostafa.training.data.remote.dto.CategoriesDTO
import com.mostafa.training.data.remote.dto.ContactDTO
import com.mostafa.training.data.remote.dto.ErrorResponse
import com.mostafa.training.data.remote.dto.FaqsDTO
import com.mostafa.training.data.remote.dto.FavoritesDTO
import com.mostafa.training.data.remote.dto.NotificationsDTO
import com.mostafa.training.data.remote.dto.ProductsDTO
import com.mostafa.training.data.remote.dto.ProductsDetailDTO
import com.mostafa.training.data.remote.dto.ProfileDTO
import com.mostafa.training.data.remote.response.NetworkResponse
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {


    @GET("banners")
    suspend fun getBanners(): NetworkResponse<BannersDTO, ErrorResponse>


    @GET("categories")
    suspend fun getCategories(): NetworkResponse<CategoriesDTO, ErrorResponse>


    @GET("products")
    suspend fun getProducts(
        @Header("Authorization") authorization: String
    ): NetworkResponse<ProductsDTO, ErrorResponse>


    @GET("products/{id}")
    suspend fun getProductDetail(
        @Path("id") id: Int,
        @Header("Authorization") authorization: String
    ): NetworkResponse<ProductsDetailDTO, ErrorResponse>

    @GET("categories/{id}")
    suspend fun getCategoryDetail(
        @Path("id") id: Int,
        @Header("Authorization") authorization: String
    ): NetworkResponse<ProductsDTO, ErrorResponse>


    @GET("notifications")
    suspend fun getNotifications(
        @Header("Authorization") authorization: String
    ): NetworkResponse<NotificationsDTO, ErrorResponse>


    @POST("products/search")
    suspend fun searchProduct(
        @Header("Authorization") authorization: String,
        @Query("text") text: String?,
    ): NetworkResponse<ProductsDTO, ErrorResponse>







    @POST("carts")
    suspend fun addOrRemoveCartItem(
        @Header("Authorization") authorization: String,
        @Query("product_id") product_id: Int
    ): NetworkResponse<AddRemoveCartItemDTO, ErrorResponse>


    @GET("carts")
    suspend fun getCartItems(
        @Header("Authorization") authorization: String,
    ): NetworkResponse<CartDTO, ErrorResponse>



    @DELETE("carts/{id}")
    suspend fun deleteFromCart(
        @Path("id") id: Int,
        @Header("Authorization") authorization: String,
    ): NetworkResponse<CartUpdateDTO, ErrorResponse>

    @PUT("carts/{id}")
    suspend fun updateCart(
        @Path("id") id: Int,
        @Header("Authorization") authorization: String,
        @Query("quantity") quantity: Int
    ): NetworkResponse<CartUpdateDTO, ErrorResponse>

    @GET("contacts")
    suspend fun getContact(
    ): NetworkResponse<ContactDTO, ErrorResponse>

    @GET("faqs")
    suspend fun getFaqs(
    ): NetworkResponse<FaqsDTO, ErrorResponse>




    @GET("profile")
    suspend fun getProfile(
        @Header("Authorization") authorization: String,
    ): NetworkResponse<ProfileDTO, ErrorResponse>


    @POST("favorites")
    suspend fun addOrRemoveFavoriteItem(
        @Header("Authorization") authorization: String,
        @Query("product_id") product_id: Int
    ): NetworkResponse<AddOrRemoveFavoritesDTO, ErrorResponse>


    @GET("favorites")
    suspend fun getFavoritesItems(
        @Header("Authorization") authorization: String,
    ): NetworkResponse<FavoritesDTO, ErrorResponse>




}