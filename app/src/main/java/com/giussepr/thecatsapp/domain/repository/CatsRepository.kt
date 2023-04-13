package com.giussepr.thecatsapp.domain.repository

import com.giussepr.thecatsapp.domain.model.Cat
import com.giussepr.thecatsapp.domain.model.Result
import kotlinx.coroutines.flow.Flow

interface CatsRepository {
    fun getCats(): Flow<Result<List<Cat>>>
}