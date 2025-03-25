package com.ekachandra.minifleettracker.core.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ekachandra.minifleettracker.core.domain.TripLog
import kotlinx.coroutines.flow.Flow

@Dao
interface TripLogDao {
    @Insert
    suspend fun insertLog(log: TripLog)

    @Query("SELECT * FROM trip_log ORDER BY timestamp DESC")
    fun getAllLogs(): Flow<List<TripLog>>
}
