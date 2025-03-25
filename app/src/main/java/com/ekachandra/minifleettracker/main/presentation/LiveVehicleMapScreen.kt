package com.ekachandra.minifleettracker.main.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.ekachandra.minifleettracker.main.presentation.models.SensorViewModel
import com.ekachandra.minifleettracker.main.presentation.models.VehicleViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import org.koin.androidx.compose.koinViewModel

@Composable
fun LiveVehicleMapScreen(
    vehicleViewModel: VehicleViewModel = koinViewModel(),
    sensorViewModel: SensorViewModel = koinViewModel()
) {
    val vehiclePosition by vehicleViewModel.vehiclePosition.collectAsState()
    val sensorData by sensorViewModel.sensorData.collectAsState()

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(vehiclePosition, 12f)
    }

    val markerState = remember { MarkerState(position = vehiclePosition) }
    markerState.position = vehiclePosition

    Scaffold(
        bottomBar = { SensorDashboard(sensorData) },
        snackbarHost = { EventAlerts(sensorData) },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            GoogleMap(
                modifier = Modifier.weight(1f),
                cameraPositionState = cameraPositionState
            ) {
                Marker(
                    state = markerState,
                    title = "Your Car"
                )
            }
        }
    }
}


