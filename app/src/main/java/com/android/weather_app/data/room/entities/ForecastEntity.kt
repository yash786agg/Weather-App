package com.android.weather_app.data.room.entities

import androidx.room.ColumnInfo
import com.android.weather_app.data.room.entities.ForecastEntityConstants.FORECAST_DAY
import com.android.weather_app.domain.model.Forecast

data class ForecastEntity(
    @ColumnInfo(name = FORECAST_DAY)
    val foreCastDayEntity: List<ForecastDayEntity>
)

fun Forecast.toEntity() = ForecastEntity(
    foreCastDayEntity = foreCastDayEntity.map { it.toEntity() },
)

fun ForecastEntity.toDomain() = Forecast(
    foreCastDayEntity = foreCastDayEntity.map { it.toDomain() },
)

object ForecastEntityConstants {
    //ColumnInfo FIELD
    const val FORECAST_DAY: String = "forecastday"
}
