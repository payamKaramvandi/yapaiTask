package com.example.core.data

class FlickrRepository(private val flickrDatasource: FlickrDatasource) {
    suspend fun searchPics(apiKey: String, text: String) = flickrDatasource.searchPic(apiKey, text)
    suspend fun getRecent(apiKey: String,perPage:Int)=flickrDatasource.getRecent(apiKey,perPage)
}