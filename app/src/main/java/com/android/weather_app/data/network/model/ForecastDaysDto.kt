package com.android.weather_app.data.network.model

import com.android.weather_app.data.network.model.ForecastDaysDtoConstant.FORECAST_DAYS_CONDITION
import com.android.weather_app.data.network.model.ForecastDaysDtoConstant.MAX_TEMP_CELSIUS
import com.android.weather_app.data.network.model.ForecastDaysDtoConstant.MIN_TEMP_CELSIUS
import com.android.weather_app.data.network.model.ForecastDaysDtoConstant.CHANCE_OF_RAIN
import com.android.weather_app.domain.model.ForecastDays
import com.squareup.moshi.Json

data class ForecastDaysDto(
    @field:Json(name = MAX_TEMP_CELSIUS)
    val maxTempCelsius: Double?,

    @field:Json(name = MIN_TEMP_CELSIUS)
    val minTempCelsius: Double?,

    @field:Json(name = CHANCE_OF_RAIN)
    val forecastOfRain: Int?,

    @field:Json(name = FORECAST_DAYS_CONDITION)
    val foreCastDayCondition: ForeCastDayConditionDto?,
)

fun ForecastDays.toDto() = ForecastDaysDto(
    maxTempCelsius = maxTempCelsius,
    minTempCelsius = minTempCelsius,
    forecastOfRain = forecastOfRain,
    foreCastDayCondition = foreCastDayCondition?.toDto()
)

fun ForecastDaysDto.toDomain() = ForecastDays(
    maxTempCelsius = maxTempCelsius,
    minTempCelsius = minTempCelsius,
    forecastOfRain = forecastOfRain,
    foreCastDayCondition = foreCastDayCondition?.toDomain()
)

object ForecastDaysDtoConstant {
    //ColumnInfo FIELDS
    const val MAX_TEMP_CELSIUS: String = "maxtemp_c"
    const val MIN_TEMP_CELSIUS: String = "mintemp_c"
    const val CHANCE_OF_RAIN: String = "daily_chance_of_rain"
    const val FORECAST_DAYS_CONDITION: String = "condition"
}
