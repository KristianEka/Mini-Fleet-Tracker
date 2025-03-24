package com.ekachandra.minifleettracker.main.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ekachandra.minifleettracker.main.presentation.models.SensorData
import com.ekachandra.minifleettracker.main.presentation.models.generateSensorData
import kotlinx.coroutines.launch

@Composable
fun SensorDashboard(
    modifier: Modifier = Modifier
) {
    var sensorData by remember { mutableStateOf(SensorData(0, false, false)) }
    val coroutineScope = rememberCoroutineScope()

    // Jalankan simulasi sensor saat UI pertama kali dibuat
    LaunchedEffect(Unit) {
        coroutineScope.launch {
            while (true) {
                sensorData = generateSensorData()
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Sensor Dashboard", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        SensorItem(label = "Speed", value = "${sensorData.speed} km/h")
        SensorItem(label = "Engine Status", value = if (sensorData.engineOn) "On" else "Off")
        SensorItem(label = "Door Status", value = if (sensorData.doorOpen) "Open" else "Closed")
    }
}

// Komponen kecil untuk menampilkan setiap item sensor
@Composable
fun SensorItem(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(text = "$label:", modifier = Modifier.weight(1f))
        Text(text = value, style = MaterialTheme.typography.bodyLarge)
    }
}
