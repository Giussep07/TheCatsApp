package com.giussepr.thecatsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.giussepr.thecatsapp.presentation.navigation.AppNavigation
import com.giussepr.thecatsapp.presentation.theme.TheCatsAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TheCatsAppTheme {
                AppNavigation(navController = rememberNavController())
            }
        }
    }
}
