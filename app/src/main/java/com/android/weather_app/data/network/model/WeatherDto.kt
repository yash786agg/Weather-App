package com.android.weather_app.data.network.model

import com.android.weather_app.data.network.model.WeatherDtoConstant.CURRENT_TEMP
import com.android.weather_app.data.network.model.WeatherDtoConstant.FORECAST
import com.android.weather_app.data.network.model.WeatherDtoConstant.LOCATION
import com.android.weather_app.domain.model.Weather
import com.squareup.moshi.Json

data class WeatherDto(
    @field:Json(name = LOCATION)
    val location: LocationDto,

    @field:Json(name = CURRENT_TEMP)
    val currentTemp: CurrentTempDto,

    @field:Json(name = FORECAST)
    val forecast: ForecastDto,
)

fun Weather.toDto() = WeatherDto(
    location = location.toDto(),
    currentTemp = currentTemp.toDto(),
    forecast = forecast.toDto()
)

fun WeatherDto.toDomain() = Weather(
    location = location.toDomain(),
    currentTemp = currentTemp.toDomain(),
    forecast = forecast.toDomain()
)

object WeatherDtoConstant {
    const val LOCATION: String = "location"
    const val CURRENT_TEMP: String = "current"
    const val FORECAST: String = "forecast"
}
