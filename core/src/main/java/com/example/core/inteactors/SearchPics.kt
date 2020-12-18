package com.example.core.inteactors

import com.example.core.data.FlickrRepository

class SearchPics constructor(private val flickrRepository: FlickrRepository) {
    suspend operator fun invoke(apiKey:String,text:String)=flickrRepository.searchPics(apiKey,text)
}