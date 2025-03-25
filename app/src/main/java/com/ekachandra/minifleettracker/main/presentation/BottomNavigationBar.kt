package com.ekachandra.minifleettracker.main.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Map
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    BottomAppBar {
        IconButton(onClick = { navController.navigate("live_map") }) {
            Icon(Icons.Default.Map, contentDescription = "Live Map")
        }
        IconButton(onClick = { navController.navigate("trip_log") }) {
            Icon(Icons.Default.History, contentDescription = "Trip Log")
        }
    }
}
