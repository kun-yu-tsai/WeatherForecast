package com.example.forecast.ui.weather.current

import androidx.lifecycle.ViewModel
import com.example.forecast.data.repository.ForecastRepository
import com.example.forecast.internal.lazyDeferred

class CurrentWeatherViewModel(
    private val forecastRepository: ForecastRepository
) : ViewModel() {
    val currentWeatherLiveData by lazyDeferred {
        forecastRepository.getCurrentWeather(true)
    }
}
