package com.ekachandra.minifleettracker.main.presentation.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ekachandra.minifleettracker.core.domain.SensorData
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.random.Random

class SensorViewModel : ViewModel() {

    private val _sensorData = MutableStateFlow(SensorData(0, false, false))
    val sensorData = _sensorData.asStateFlow()

    init {
        startSensorSimulation()
    }

    private fun startSensorSimulation() {
        viewModelScope.launch {
            while (true) {
                delay(2000)
                _sensorData.value = SensorData(
                    speed = Random.nextInt(0, 120),
                    engineOn = Random.nextBoolean(),
                    doorOpen = Random.nextBoolean()
                )
            }
        }
    }
}
