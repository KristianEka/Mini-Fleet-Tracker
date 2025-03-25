package com.ekachandra.minifleettracker.main.presentation

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ekachandra.minifleettracker.main.presentation.models.TripLogViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun TripLogScreen(tripLogViewModel: TripLogViewModel = koinViewModel()) {
    val tripLogsState = tripLogViewModel.tripLogs.collectAsStateWithLifecycle()
    val tripLogs = tripLogsState.value

    LaunchedEffect(tripLogs) {
        Log.d("TripLogScreen", "Jumlah Trip Logs: ${tripLogs.size}")
    }

    LazyColumn {
        if (tripLogs.isEmpty()) {
            item {
                Text(text = "No trip logs available")
            }
        } else {
            items(tripLogs) { log ->
                Text(text = "📍 Lat: ${log.latitude}, Lng: ${log.longitude}, 🕒 ${log.timestamp}")
            }
        }
    }
}
