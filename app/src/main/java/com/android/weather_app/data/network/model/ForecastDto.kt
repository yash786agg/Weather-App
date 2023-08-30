package com.android.weather_app.data.network.model

import com.android.weather_app.data.network.model.ForecastDtoConstant.FORECAST_DAY
import com.android.weather_app.domain.model.Forecast
import com.squareup.moshi.Json

data class ForecastDto(
    @field:Json(name = FORECAST_DAY)
    val foreCastDayDto: List<ForecastDayDto>
)

fun Forecast.toDto() = ForecastDto(
    foreCastDayDto = foreCastDayEntity.map { it.toDto() },
)

fun ForecastDto.toDomain() = Forecast(
    foreCastDayEntity = foreCastDayDto.map { it.toDomain() },
)

object ForecastDtoConstant {
    //ColumnInfo FIELD
    const val FORECAST_DAY: String = "forecastday"
}
