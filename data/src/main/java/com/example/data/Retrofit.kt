package com.example.data


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private val BASE_URL = "https://google.com"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface RetrofitApi{
    //implement your own function
    @GET("/")
    suspend fun getData(): String
}

object Retrofit {
    val retrofitApi: RetrofitApi by lazy {
        retrofit.create(RetrofitApi::class.java)
    }
}