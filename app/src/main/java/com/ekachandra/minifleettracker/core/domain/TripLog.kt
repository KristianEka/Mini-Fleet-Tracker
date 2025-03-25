package com.ekachandra.minifleettracker.core.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trip_log")
data class TripLog(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val latitude: Double,
    val longitude: Double,
    val timestamp: Long
)

