package com.android.weather_app.data.network.model

import com.android.weather_app.data.network.model.ForecastDayDtoConstant.CHANCE_OF_RAIN
import com.android.weather_app.data.network.model.ForecastDayDtoConstant.DATE
import com.android.weather_app.data.network.model.ForecastDayDtoConstant.DAY
import com.android.weather_app.data.network.model.ForecastDayDtoConstant.HOUR
import com.android.weather_app.domain.model.ForecastDay
import com.squareup.moshi.Json

data class ForecastDayDto(
    @field:Json(name = DATE)
    val forecastDate: String,

    @field:Json(name = CHANCE_OF_RAIN)
    val forecastOfRain: Int,

    @field:Json(name = DAY)
    val forecastDays: ForecastDaysDto,

    @field:Json(name = HOUR)
    val forecastDayHourList: List<ForecastDayHourListDto>
)

fun ForecastDay.toDto() = ForecastDayDto(
    forecastDate = forecastDate,
    forecastOfRain = forecastOfRain,
    forecastDays = forecastDays.toDto(),
    forecastDayHourList = forecastDayHourList.map { it.toDto() }
)

fun ForecastDayDto.toDomain() = ForecastDay(
    forecastDate = forecastDate,
    forecastOfRain = forecastOfRain,
    forecastDays = forecastDays.toDomain(),
    forecastDayHourList = forecastDayHourList.map { it.toDomain() }
)


object ForecastDayDtoConstant {
    const val DATE: String = "date"
    const val CHANCE_OF_RAIN: String = "daily_chance_of_rain"
    const val DAY: String = "day"
    const val HOUR: String = "hour"
}
