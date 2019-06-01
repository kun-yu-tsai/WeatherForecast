package com.example.forecast.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.forecast.data.db.entity.CurrentWeatherEntry


@Database(
    entities = [CurrentWeatherEntry::class], // tables
    version = 1
)
abstract class ForecastDatabase : RoomDatabase() {
    // Dao to be used by upper layer (Repository)
    abstract fun currentWeatherDao(): CurrentWeatherDao

    companion object {
        private var instance: ForecastDatabase? = null

        // When a static class is loaded, a companion object is also instantiated.
        // The name of the object is "ForecastDatabase", and its type is ForecastDatabase.Companion
        // What we do here is we overload the invoke operator for ForecastDatabase object.
        // Lastly, we encapsulate the initialisation of the instance in the ForecastDatabase invocation.
        operator fun invoke(context: Context) = instance ?: synchronized(this){
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context,
                ForecastDatabase::class.java,
                "forecast.db"
            ).build()

    }
}