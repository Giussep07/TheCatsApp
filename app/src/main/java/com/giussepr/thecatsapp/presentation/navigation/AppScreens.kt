package com.giussepr.thecatsapp.presentation.navigation

sealed class AppScreens(val route: String) {
    object Home : AppScreens("home")
}