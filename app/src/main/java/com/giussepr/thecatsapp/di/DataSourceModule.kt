package com.giussepr.thecatsapp.di

import com.giussepr.thecatsapp.data.api.ApiCats
import com.giussepr.thecatsapp.data.repository.datasource.remote.CatsRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object DataSourceModule {

    @Provides
    fun provideCatsRemoteDataSource(apiCats: ApiCats): CatsRemoteDataSource =
        CatsRemoteDataSource(apiCats)
}