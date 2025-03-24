package com.ekachandra.minifleettracker.main.presentation

import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import com.ekachandra.minifleettracker.main.presentation.models.SensorData
import kotlinx.coroutines.launch

@Composable
fun EventAlerts(sensorData: SensorData) {
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    LaunchedEffect(sensorData) {
        val alertMessage = when {
            sensorData.speed > 80 -> "⚠️ Kecepatan berlebihan: ${sensorData.speed} km/h!"
            sensorData.doorOpen && sensorData.speed > 0 -> "🚪 Pintu terbuka saat kendaraan berjalan!"
            sensorData.engineOn -> "🔄 Mesin dinyalakan!"
            else -> null
        }

        alertMessage?.let {
            coroutineScope.launch {
                snackbarHostState.showSnackbar(it)
            }
        }
    }

    SnackbarHost(hostState = snackbarHostState)
}
