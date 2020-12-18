package com.example.yapai.framework.di

import com.example.core.data.FlickrDatasource
import com.example.yapai.framework.repository.FlickrRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
abstract class HiltFlickrDataSource {

    @Binds
    abstract fun bindFlickrDatasource(
        flickrRepository: FlickrRepository
    ):FlickrDatasource
}
