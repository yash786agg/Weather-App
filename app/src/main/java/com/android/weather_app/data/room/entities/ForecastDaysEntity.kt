package com.android.weather_app.data.room.entities

import androidx.room.ColumnInfo
import com.android.weather_app.data.room.entities.ForecastDaysEntityConstants.FORECAST_DAYS_CONDITION
import com.android.weather_app.data.room.entities.ForecastDaysEntityConstants.MAX_TEMP_CELSIUS
import com.android.weather_app.data.room.entities.ForecastDaysEntityConstants.MIN_TEMP_CELSIUS
import com.android.weather_app.domain.model.ForecastDays

data class ForecastDaysEntity(
    @ColumnInfo(name = MAX_TEMP_CELSIUS)
    val maxTempCelsius: Double,

    @ColumnInfo(name = MIN_TEMP_CELSIUS)
    val minTempCelsius: Double,

    @ColumnInfo(name = FORECAST_DAYS_CONDITION)
    val foreCastDayCondition: ForeCastDayConditionEntity,
)

fun ForecastDays.toEntity() = ForecastDaysEntity(
    maxTempCelsius = maxTempCelsius,
    minTempCelsius = minTempCelsius,
    foreCastDayCondition = foreCastDayCondition.toEntity()
)

fun ForecastDaysEntity.toDomain() = ForecastDays(
    maxTempCelsius = maxTempCelsius,
    minTempCelsius = minTempCelsius,
    foreCastDayCondition = foreCastDayCondition.toDomain()
)

object ForecastDaysEntityConstants {
    //ColumnInfo FIELDS
    const val MAX_TEMP_CELSIUS: String = "maxtemp_c"
    const val MIN_TEMP_CELSIUS: String = "mintemp_c"
    const val FORECAST_DAYS_CONDITION: String = "condition"
}