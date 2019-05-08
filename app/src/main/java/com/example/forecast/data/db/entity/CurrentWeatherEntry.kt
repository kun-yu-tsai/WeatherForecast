package com.example.forecast.data.db.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

const val CURRENT_WEATHER_ID = 0

@Entity(tableName = "current_weather")
data class CurrentWeatherEntry(
    @PrimaryKey(autoGenerate = false)
    var id: Int = CURRENT_WEATHER_ID,

    val cloud: Int,

    @Embedded(prefix = "condition_")
    val condition: Condition,

    @SerializedName("feelslike_c")
    val feelslikeC: Double,
    @SerializedName("feelslike_f")
    val feelslikeF: Double,
    @SerializedName("gust_kph")
    val gustKph: Double,
    @SerializedName("gust_mph")
    val gustMph: Double,
    @SerializedName("is_day")
    val isDay: Int,
    @SerializedName("precip_in")
    val precipIn: Int,
    @SerializedName("precip_mm")
    val precipMm: Int,
    @SerializedName("temp_c")
    val tempC: Int,
    @SerializedName("temp_f")
    val tempF: Double,
    val uv: Int,
    @SerializedName("vis_km")
    val visKm: Int,
    @SerializedName("vis_miles")
    val visMiles: Int,
    @SerializedName("wind_dir")
    val windDir: String,
    @SerializedName("wind_kph")
    val windKph: Double,
    @SerializedName("wind_mph")
    val windMph: Double
)