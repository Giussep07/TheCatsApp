package com.giussepr.thecatsapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.giussepr.thecatsapp.presentation.screens.home.HomeScreen

@Composable
fun AppNavigation(navController: NavHostController, startDestination: String = AppScreens.Home.route) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(AppScreens.Home.route) {
            HomeScreen(navController)
        }
    }
}