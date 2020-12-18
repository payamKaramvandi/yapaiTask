package com.example.core.inteactors

import com.example.core.data.FlickrRepository

class GetRecent constructor(private val flickrRepository: FlickrRepository) {
    suspend operator fun invoke(apiKey: String, perPage: Int) =
        flickrRepository.getRecent(apiKey,perPage)
}