package com.example.yapai.framework.network

import com.example.core.domain.JsonFlickrApi
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FlickrApi {


    @GET("rest/")
    suspend fun getRecent(
        @Query("method") method: String="flickr.photos.getRecent",
        @Query("api_key") api_key: String,
        @Query("per_page") perPage: Int,
        @Query("format") format: String = "json",
        @Query("nojsoncallback") nojsoncallback: Int = 1
    ): Response<JsonFlickrApi>

    @GET("rest/")
    suspend fun searchPics(
        @Query("text") text: String = "",
        @Query("page") page: Int = 1,
        @Query("method") method: String = "flickr.photos.search",
        @Query("api_key") apiKey: String ,
        @Query("format") format: String = "json",
        @Query("nojsoncallback") nojsoncallback: Int = 1
    ): JsonFlickrApi

    companion object {

        const val BASE_URL = "https://www.flickr.com/services/"
    }
}