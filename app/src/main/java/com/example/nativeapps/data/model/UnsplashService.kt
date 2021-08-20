package com.example.nativeapps.data.model

import retrofit2.http.GET

interface UnsplashService {
    @GET("/200/300")
    suspend fun searchPhotos()
}
