package com.example.forecast.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.forecast.data.network.response.CurrentWeatherResponse
import com.example.forecast.internal.NoConnectivityException

class WeatherNetworkDataSourceImpl(
    private val apixApiService: ApixApiService
) : WeatherNetworkDataSource {

    private val _currentWeather = MutableLiveData<CurrentWeatherResponse>()
    override val currentWeather: LiveData<CurrentWeatherResponse>
        get() = _currentWeather

    override suspend fun syncCurrentWeather(location: String, language: String) {
        try {
            _currentWeather.postValue(apixApiService.getCurrentWeather(location, language).await())
        }
        catch (e: NoConnectivityException){
            Log.e("Connectivity", "No internet connection.", e)
        }
    }
}