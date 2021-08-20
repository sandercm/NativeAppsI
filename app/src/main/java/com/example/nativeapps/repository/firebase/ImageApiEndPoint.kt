package com.example.nativeapps.repository.firebase

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ImageApiEndPoint {
    @GET("list")
    fun getImages(): Call<List<Image>>

    companion object {

        var BASE_URL = "https://picsum.photos/v2/"

        fun create(): ImageApiEndPoint {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(ImageApiEndPoint::class.java)
        }
    }
}
