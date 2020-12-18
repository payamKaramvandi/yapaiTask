package com.example.yapai.presentation.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.JsonFlickrApi
import com.example.yapai.framework.Interactors
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.launch

@ActivityRetainedScoped
class FlickrViewModel @ViewModelInject constructor(
    private val interactors: Interactors,
    private val apiKey: String,

    ) : ViewModel() {
    private val _recentLiveData = MutableLiveData<JsonFlickrApi>()
    val recentLiveData: LiveData<JsonFlickrApi>
        get() = _recentLiveData

    fun getRecent(perPage: Int) {
        viewModelScope.launch {

            _recentLiveData.value =
                interactors.getRecent.invoke(
                    apiKey = apiKey,
                    perPage = perPage
                )
        }
    }
    fun searchFor(query: String, page: Int,apiKey: String) {
        viewModelScope.launch {
            _recentLiveData.value=interactors.searchPics.invoke(
                apiKey = apiKey,
                text = query
            )
        }
    }
}