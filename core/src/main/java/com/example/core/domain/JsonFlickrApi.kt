package com.example.core.domain

import java.io.Serializable

data class JsonFlickrApi(

    val photos: Photos?,
    val stat: String?
):Serializable