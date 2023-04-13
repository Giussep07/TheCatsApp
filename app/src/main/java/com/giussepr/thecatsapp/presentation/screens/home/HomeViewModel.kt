package com.giussepr.thecatsapp.presentation.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.giussepr.thecatsapp.domain.model.Cat
import com.giussepr.thecatsapp.domain.model.fold
import com.giussepr.thecatsapp.domain.repository.CatsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val catsRepository: CatsRepository
): ViewModel() {

    var uiState by mutableStateOf(HomeUiState())
        private set

    private fun getCats() {
        catsRepository.getCats().map { result ->
            result.fold(
                onSuccess = { cats ->
                    uiState = uiState.copy(
                        isLoading = false,
                        cats = cats
                    )
                },
                onFailure = {
                    uiState = uiState.copy(
                        isLoading = false,
                        errorMessage = it.message
                    )
                }
            )
        }
            .onStart { uiState = uiState.copy(isLoading = true) }
            .flowOn(Dispatchers.IO).launchIn(viewModelScope)
    }

    fun onUiEvent(uiEvent: HomeUiEvent) {
        when (uiEvent) {
            is HomeUiEvent.LoadCats -> { getCats() }
        }
    }

    data class HomeUiState(
        val isLoading: Boolean = false,
        val errorMessage: String = "",
        val cats: List<Cat> = emptyList()
    )

    sealed class HomeUiEvent {
        object LoadCats : HomeUiEvent()
    }
}