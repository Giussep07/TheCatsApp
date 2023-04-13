package com.giussepr.thecatsapp.di

import com.giussepr.thecatsapp.data.repository.CatsRepositoryImpl
import com.giussepr.thecatsapp.data.repository.datasource.remote.CatsRemoteDataSource
import com.giussepr.thecatsapp.domain.repository.CatsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    fun provideCatsRepository(
        catsRemoteDataSource: CatsRemoteDataSource
    ): CatsRepository = CatsRepositoryImpl(catsRemoteDataSource)

}
