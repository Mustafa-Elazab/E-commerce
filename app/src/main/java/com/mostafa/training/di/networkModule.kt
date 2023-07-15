package com.mostafa.training.di

import com.mostafa.training.data.remote.ApiServices
import com.mostafa.training.data.remote.response.NetworkResponseAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://student.valuxapps.com/api/"

val networkModule = module {
    single { providesHttpLoggingInterceptor() }
    single { providesOkHttpClient(get()) }
    single { provideRetrofit(get()) }
    single { provideApiService(get()) }
}

fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor()
    .apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
    OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .addInterceptor { chain ->
            val newRequest = chain.request().newBuilder()
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json")
                .addHeader("lang", "en")
                .build()
            chain.proceed(newRequest)
        }
        .build()

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
    .client(okHttpClient)
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(NetworkResponseAdapterFactory())
    .build()

fun provideApiService(retrofit: Retrofit): ApiServices =
    retrofit.create(ApiServices::class.java)
