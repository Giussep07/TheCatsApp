package com.giussepr.thecatsapp.data.repository.datasource.remote

import com.giussepr.thecatsapp.data.api.ApiCats
import javax.inject.Inject

class CatsRemoteDataSource @Inject constructor(
    private val apiCats: ApiCats
) {
    suspend fun getCats() = apiCats.getCats()
}