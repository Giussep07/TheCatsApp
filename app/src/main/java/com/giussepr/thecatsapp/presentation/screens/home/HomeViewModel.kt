package com.giussepr.thecatsapp.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giussepr.thecatsapp.domain.model.fold
import com.giussepr.thecatsapp.domain.repository.CatsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val catsRepository: CatsRepository
): ViewModel() {

    fun getCats() {
        catsRepository.getCats().map { result ->
            result.fold(
                onSuccess = { cats ->
                    // TODO: Show cats
                },
                onFailure = {
                    // TODO: Show error
                }
            )
        }.flowOn(Dispatchers.IO).launchIn(viewModelScope)
    }
}