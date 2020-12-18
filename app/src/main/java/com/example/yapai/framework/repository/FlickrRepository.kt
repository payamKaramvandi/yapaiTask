package com.example.yapai.framework.repository

import com.example.core.data.FlickrDatasource
import com.example.core.domain.JsonFlickrApi
import com.example.yapai.framework.network.FlickrApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FlickrRepository @Inject constructor(
    private val flickrApi: FlickrApi
):FlickrDatasource{
    override suspend fun searchPic(apiKey: String, text: String): JsonFlickrApi {
        return flickrApi.searchPics(apiKey=apiKey,text=text)
    }

    override suspend fun getRecent(apiKey: String, perPage: Int):JsonFlickrApi {
        return flickrApi.getRecent(api_key = apiKey,perPage =perPage ).let {
            JsonFlickrApi(it.body()?.photos,it.body()?.stat)
        }
    }

}