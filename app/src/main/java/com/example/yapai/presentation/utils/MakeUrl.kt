package com.example.yapai.presentation.utils

import com.example.core.domain.Photo

class MakeUrl(val item: Photo) {
    operator fun invoke(): String {
        return "https://live.staticflickr.com/"
            .plus(item.server)
            .plus("/")
            .plus(item.id)
            .plus("_")
            .plus(item.secret)
            .plus("_")
            .plus("w") //small size
            .plus(".jpg")
    }
}