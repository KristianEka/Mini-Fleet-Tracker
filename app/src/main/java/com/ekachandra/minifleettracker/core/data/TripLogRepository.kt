package com.ekachandra.minifleettracker.core.data

import com.ekachandra.minifleettracker.core.domain.TripLog
import kotlinx.coroutines.flow.Flow

class TripLogRepository(private val tripLogDao: TripLogDao) {

    suspend fun insertLog(log: TripLog) {
        tripLogDao.insertLog(log)
    }

    fun getAllLogs(): Flow<List<TripLog>> {
        return tripLogDao.getAllLogs()
    }
}