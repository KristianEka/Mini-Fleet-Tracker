package com.ekachandra.minifleettracker.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ekachandra.minifleettracker.core.navigation.BottomNavigationBar
import com.ekachandra.minifleettracker.core.navigation.Screen
import com.ekachandra.minifleettracker.main.presentation.dashboard.DashboardScreen
import com.ekachandra.minifleettracker.main.presentation.event.EventScreen
import com.ekachandra.minifleettracker.main.presentation.live.LiveVehicleMap

@Composable
fun MainScreen(
    modifier: Modifier = Modifier
) {

    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.LiveVehicleMap.route,
            Modifier.padding(innerPadding)
        ) {
            composable(Screen.LiveVehicleMap.route) { LiveVehicleMap() }
            composable(Screen.Dashboard.route) { DashboardScreen() }
            composable(Screen.Event.route) { EventScreen() }
        }
    }


}