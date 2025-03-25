package com.ekachandra.minifleettracker.main.presentation.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ekachandra.minifleettracker.core.data.TripLogRepository
import com.ekachandra.minifleettracker.core.domain.TripLog
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class VehicleViewModel(
    private val tripLogRepository: TripLogRepository
) : ViewModel() {
    private val _vehiclePosition =
        MutableStateFlow(LatLng(-6.2088, 106.8456))
    val vehiclePosition = _vehiclePosition.asStateFlow()

    private val vehicleRoute = listOf(
        LatLng(-6.2088, 106.8456),
        LatLng(-6.2095, 106.8470),
        LatLng(-6.2100, 106.8485),
        LatLng(-6.2112, 106.8500),
        LatLng(-6.2125, 106.8520),
        LatLng(-6.2140, 106.8545)
    )

    private var routeIndex = 0

    init {
        startVehicleSimulation()
    }

    private fun startVehicleSimulation() {
        viewModelScope.launch {
            while (true) {
                delay(2000)
                routeIndex = (routeIndex + 1) % vehicleRoute.size
                val newPosition = vehicleRoute[routeIndex]
                _vehiclePosition.value = newPosition

                tripLogRepository.insertLog(
                    TripLog(
                        latitude = newPosition.latitude,
                        longitude = newPosition.longitude,
                        timestamp = System.currentTimeMillis()
                    )
                )
            }
        }
    }
}

