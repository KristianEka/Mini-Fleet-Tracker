package com.ekachandra.minifleettracker.main.presentation.models

import kotlinx.coroutines.delay
import kotlin.random.Random

data class SensorData(
    val speed: Int,
    val engineOn: Boolean,
    val doorOpen: Boolean
)

suspend fun generateSensorData(): SensorData {
    while (true) {
        delay(2000) // Update setiap 2 detik
        val speed = Random.nextInt(0, 120) // Kecepatan acak antara 0-120 km/h
        val engineOn = Random.nextBoolean() // Mesin on/off acak
        val doorOpen = Random.nextBoolean() // Pintu open/closed acak
        return SensorData(speed, engineOn, doorOpen)
    }
}
