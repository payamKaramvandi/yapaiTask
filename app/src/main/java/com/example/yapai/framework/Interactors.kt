package com.example.yapai.framework

import com.example.core.inteactors.GetRecent
import com.example.core.inteactors.SearchPics

data class Interactors(
        val searchPics: SearchPics ,
        val getRecent: GetRecent
)