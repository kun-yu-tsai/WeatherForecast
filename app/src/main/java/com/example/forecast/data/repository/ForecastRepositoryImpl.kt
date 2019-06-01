package com.example.forecast.data.repository

import androidx.lifecycle.LiveData
import com.example.forecast.data.db.CurrentWeatherDao
import com.example.forecast.data.db.entity.CurrentWeatherEntry
import com.example.forecast.data.db.unitlocalized.UnitSpecificCurrentWeatherEntry
import com.example.forecast.data.network.WeatherNetworkDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ForecastRepositoryImpl(
    private val currentWeatherDao: CurrentWeatherDao,
    private val weatherNetworkDataSource: WeatherNetworkDataSource
) : ForecastRepository {

    init {
        // whenever currentWeather in weatherNetwork is updated, we save the updated value in database.
        weatherNetworkDataSource.currentWeather.observeForever {
            persistCurrentWeatherData(it.currentWeatherEntry)
        }
    }

    private fun persistCurrentWeatherData(currentWeatherEntry: CurrentWeatherEntry) {
        GlobalScope.launch(Dispatchers.IO) {
            currentWeatherDao.upsert(currentWeatherEntry)
        }
    }

    override suspend fun getCurrentWeather(metric: Boolean): LiveData<out UnitSpecificCurrentWeatherEntry> { // ? extends XXX
        /*
        if network -> get data from network
        otherwise, get data from db
         */
        return withContext(Dispatchers.IO) {
            // We will update the live data in this object.
            weatherNetworkDataSource.syncCurrentWeather("London", "en")

            // after this point, the database has been updated.
            // we need to return a liveData from the database, and this liveData is going to be used in View. I mean,
            // the LiveData will be bound with the views in View.

            return@withContext currentWeatherDao.getWeatherImperial()
        }
    }
}