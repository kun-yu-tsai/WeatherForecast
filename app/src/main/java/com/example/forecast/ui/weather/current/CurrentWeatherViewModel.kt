package com.example.forecast.ui.weather.current

import androidx.lifecycle.ViewModel
import com.example.forecast.data.repository.ForecastRepository
import com.example.forecast.internal.UnitSystem
import com.example.forecast.internal.lazyDeferred

class CurrentWeatherViewModel(
    private val forecastRepository: ForecastRepository
) : ViewModel() {
    private val unitSystem = UnitSystem.METRIC

    val isMetricUnit : Boolean
        get() = unitSystem == UnitSystem.METRIC

    val currentWeatherLiveData by lazyDeferred {
        forecastRepository.getCurrentWeather(isMetricUnit)
    }
}
