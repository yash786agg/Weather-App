package com.android.weather_app.data.network.model

import com.android.weather_app.data.network.model.ForecastDayDtoConstant.DAY
import com.android.weather_app.data.network.model.ForecastDayDtoConstant.HOUR
import com.android.weather_app.domain.model.ForecastDay
import com.squareup.moshi.Json

data class ForecastDayDto(
    @field:Json(name = DAY)
    val forecastDays: ForecastDaysDto,

    @field:Json(name = HOUR)
    val forecastDayHourList: List<ForecastDayHourListDto>
)

fun ForecastDay.toDto() = ForecastDayDto(
    forecastDays = forecastDays.toDto(),
    forecastDayHourList = forecastDayHourList.map { it.toDto() }
)

fun ForecastDayDto.toDomain() = ForecastDay(
    forecastDays = forecastDays.toDomain(),
    forecastDayHourList = forecastDayHourList.map { it.toDomain() }
)


object ForecastDayDtoConstant {
    const val DAY: String = "day"
    const val HOUR: String = "hour"
}
