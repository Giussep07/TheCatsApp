package com.giussepr.thecatsapp.data.api

import com.giussepr.thecatsapp.data.model.CatResponseDTO
import retrofit2.Response
import retrofit2.http.GET

interface ApiCats {

    @GET("breeds")
    suspend fun getCats(): Response<List<CatResponseDTO>>
}