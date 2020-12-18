package com.example.yapai.framework.di

import com.example.core.data.FlickrDatasource
import com.example.core.data.FlickrRepository

import com.example.core.inteactors.GetRecent
import com.example.core.inteactors.SearchPics
import com.example.yapai.framework.Interactors
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@InstallIn(ActivityComponent::class)
@Module
class HiltViewModel {



    @Provides
    fun provideFlickrRepository(flickrDatasource: FlickrDatasource) = FlickrRepository(flickrDatasource)

    @Provides
    fun provideInterActors(flickrRepository: FlickrRepository) =
        Interactors(
            SearchPics(flickrRepository),
            GetRecent(flickrRepository)
        )

    @Provides
    fun provideApiKey()="9837be3422713b1c45cd4c4df8688d43"
}