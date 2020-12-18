package com.example.core.data

import com.example.core.domain.JsonFlickrApi
import kotlinx.coroutines.flow.Flow

interface FlickrDatasource {
    suspend fun searchPic(apiKey:String,text:String): JsonFlickrApi
    suspend fun getRecent(apiKey: String,perPage:Int):JsonFlickrApi
}