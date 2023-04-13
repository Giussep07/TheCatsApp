@file:OptIn(ExperimentalCoroutinesApi::class)

package com.giussepr.thecatsapp.presentation.home

import com.giussepr.thecatsapp.domain.model.Cat
import com.giussepr.thecatsapp.domain.model.DomainException
import com.giussepr.thecatsapp.domain.model.Result
import com.giussepr.thecatsapp.domain.repository.CatsRepository
import com.giussepr.thecatsapp.presentation.screens.home.HomeViewModel
import com.giussepr.thecatsapp.utils.DispatcherProvider
import com.giussepr.thecatsapp.utils.TestDispatcherProvider
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class HomeViewModelTest {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var dispatcherProvider: DispatcherProvider

    private val catsRepository: CatsRepository = mockk()

    @Before
    fun setUp() {
        dispatcherProvider = TestDispatcherProvider()

        homeViewModel = HomeViewModel(
            catsRepository = catsRepository,
            dispatcherProvider = dispatcherProvider
        )

        Dispatchers.setMain(dispatcherProvider.main)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test getCats() success`() {
        runTest {
            // Prepare
            val expected = buildList {
                add(Cat(id = "1", breedName = "Abyssinian", origin = "Egypt", intelligence = 5, imageUrl = "https://cdn2.thecatapi.com/images/O3btzLlsO.png"))
                add(Cat(id = "2", breedName = "Aegean", origin = "Greece", intelligence = 5, imageUrl = "https://cdn2.thecatapi.com/images/O3btzLlsO.png"))
                add(Cat(id = "3", breedName = "American Bobtail", origin = "United States", intelligence = 5, imageUrl = "https://cdn2.thecatapi.com/images/O3btzLlsO.png"))
                add(Cat(id = "4", breedName = "American Curl", origin = "United States", intelligence = 5, imageUrl = "https://cdn2.thecatapi.com/images/O3btzLlsO.png"))
                add(Cat(id = "5", breedName = "American Shorthair", origin = "United States", intelligence = 5, imageUrl = "https://cdn2.thecatapi.com/images/O3btzLlsO.png"))
            }

            every { catsRepository.getCats() } returns flowOf(Result.Success(expected))

            // Execute
            homeViewModel.getCats()

            // Assert
            assertEquals(expected.size, homeViewModel.uiState.cats.size)
        }
    }

    @Test
    fun `test getCats() error`() {
        runTest {
            // Prepare
            val expected = "No cats found"

            every { catsRepository.getCats() } returns flowOf(Result.Error(DomainException(expected)))

            // Execute
            homeViewModel.getCats()

            // Assert
            assertEquals(expected, homeViewModel.uiState.errorMessage)
        }
    }
}