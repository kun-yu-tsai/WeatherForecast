package com.example.forecast.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.forecast.data.db.entity.CurrentWeatherEntry


// Set tables
@Database(
    entities = [CurrentWeatherEntry::class],
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


//@Database(
//    entities = [CurrentWeatherEntry::class],
//    version = 1
//)
//abstract class ForecastDatabase : RoomDatabase() {
//    abstract fun currentWeatherDao(): CurrentWeatherDao
//
//    companion object {
//        // Apply volatile to make sure this variable keeps its changes among all threads.
//        @Volatile
//        private var instance: ForecastDatabase? = null
//        // A lock to be used in synchronization block
//        private val LOCK = Any()
//
//        // Initialisation is executed in synchronization block.
//        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
//            instance ?: buildDatabase(context).also { instance = it }
//        }
//
//        private fun buildDatabase(context: Context) =
//            Room.databaseBuilder(
//                context,
//                ForecastDatabase::class.java,
//                "forecast.db"
//            ).build()
//    }
//}