package com.example.forecast.data.network

import androidx.lifecycle.LiveData
import com.example.forecast.data.network.response.CurrentWeatherResponse

interface WeatherNetworkDataSource {
    val currentWeather: LiveData<CurrentWeatherResponse>

    suspend fun syncCurrentWeather(
        location: String,
        language : String
    )
}