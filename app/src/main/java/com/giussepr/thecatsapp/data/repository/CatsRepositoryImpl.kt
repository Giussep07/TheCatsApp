package com.giussepr.thecatsapp.data.repository

import com.giussepr.thecatsapp.data.repository.datasource.remote.CatsRemoteDataSource
import com.giussepr.thecatsapp.domain.model.Cat
import com.giussepr.thecatsapp.domain.model.DomainException
import com.giussepr.thecatsapp.domain.model.Result
import com.giussepr.thecatsapp.domain.repository.CatsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CatsRepositoryImpl @Inject constructor(
    private val catsRemoteDataSource: CatsRemoteDataSource
): CatsRepository {

    override fun getCats(): Flow<Result<List<Cat>>> = flow {
        try {
            val response = catsRemoteDataSource.getCats()
            if (response.isSuccessful) {
                response.body()?.let { catsResponse ->
                    if (catsResponse.isNotEmpty()) {
                        val cats = catsResponse.map { catResponse -> Cat.fromResponseDTO(catResponse) }
                        emit(Result.Success(cats))
                    } else {
                        emit(Result.Error(DomainException("No cats found")))
                    }
                }
            } else {
                emit(Result.Error(DomainException("Error getting cats: ${response.errorBody()}")))
            }
        } catch (e: Exception) {
            emit(Result.Error(DomainException("Error getting cats: ${e.message}")))
        }
    }
}