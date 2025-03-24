package com.ekachandra.minifleettracker.core.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Map
import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItem(
    val title: String,
    val icon: ImageVector,
    val route: String,
)

sealed class Screen(val route: String) {
    data object LiveVehicleMap : Screen("live_vehicle_map")
    data object Dashboard: Screen("sensor_dashboard")
    data object Event: Screen("event_alerts")
}

val navigationItem = listOf(
    NavigationItem(
        title = "Live Map",
        icon = Icons.Default.Map,
        route = Screen.LiveVehicleMap.route,
    ),
    NavigationItem(
        title = "Dashboard",
        icon = Icons.Default.Dashboard,
        route = Screen.Dashboard.route,
    ),
    NavigationItem(
        title = "Event",
        icon = Icons.Default.Event,
        route = Screen.Event.route,
    ),
)