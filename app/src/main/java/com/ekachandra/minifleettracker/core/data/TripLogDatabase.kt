package com.ekachandra.minifleettracker.core.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ekachandra.minifleettracker.core.domain.TripLog

@Database(entities = [TripLog::class], version = 1, exportSchema = false)
abstract class TripLogDatabase : RoomDatabase() {
    abstract fun tripLogDao(): TripLogDao

    companion object {
        @Volatile
        private var INSTANCE: TripLogDatabase? = null

        fun getDatabase(context: Context): TripLogDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TripLogDatabase::class.java,
                    "trip_log_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
