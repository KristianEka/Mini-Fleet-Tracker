package com.ekachandra.minifleettracker.main.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.ekachandra.minifleettracker.R
import com.ekachandra.minifleettracker.core.presentation.util.bitmapDescriptorFromVector
import com.ekachandra.minifleettracker.main.presentation.models.SensorData
import com.ekachandra.minifleettracker.main.presentation.models.generateSensorData
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun LiveVehicleMapScreen(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    val vehicleRoute = listOf(
        LatLng(-6.2088, 106.8456),
        LatLng(-6.2095, 106.8470),
        LatLng(-6.2100, 106.8485),
        LatLng(-6.2112, 106.8500),
        LatLng(-6.2125, 106.8520),
        LatLng(-6.2140, 106.8545),
    )

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(vehicleRoute.first(), 12f)
    }

    var vehiclePosition by remember { mutableStateOf(vehicleRoute.first()) }
    var routeIndex by remember { mutableIntStateOf(0) }
    val markerState = remember { MarkerState(position = vehiclePosition) }
    var sensorData by remember { mutableStateOf(SensorData(0, false, false)) }
    val coroutineScope = rememberCoroutineScope()

    // Pindahkan icon ke remember agar tidak dibuat ulang
    val carIcon = remember {
        bitmapDescriptorFromVector(context, R.drawable.car_icon)
    }

    LaunchedEffect(Unit) {
        coroutineScope.launch {
            while (true) {
                sensorData = generateSensorData()
            }
        }
    }

    LaunchedEffect(Unit) {
        while (true) {
            delay(2000)
            routeIndex = (routeIndex + 1) % vehicleRoute.size
            vehiclePosition = vehicleRoute[routeIndex]
            markerState.position = vehiclePosition
            cameraPositionState.animate(
                update = CameraUpdateFactory.newLatLng(vehiclePosition),
                durationMs = 1000
            )
        }
    }

    // ✅ Perbaikan: Tambahkan WindowInsets.navigationBars agar dashboard tidak tertutup
    Scaffold(
        snackbarHost = { EventAlerts(sensorData) },
        bottomBar = {
            SensorDashboard(
                modifier = Modifier
                    .fillMaxWidth()
                    .navigationBarsPadding() // ✅ Hindari overlap dengan tombol navigasi
                    .imePadding() // ✅ Tambahkan padding untuk keyboard jika ada
                    .padding(bottom = 16.dp) // ✅ Tambahan ruang agar lebih nyaman
            )
        },
        contentWindowInsets = WindowInsets.navigationBars // ✅ Hindari tombol navigasi
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .systemBarsPadding() // ✅ Pastikan UI tidak tertutup status bar dan navbar
        ) {
            GoogleMap(
                modifier = Modifier.weight(1f),
                cameraPositionState = cameraPositionState
            ) {
                Marker(
                    state = markerState,
                    title = "Your Car",
                    icon = carIcon
                )
            }
        }
    }
}

