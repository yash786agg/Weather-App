package com.android.weather_app.data.room.entities

import androidx.room.ColumnInfo
import com.android.weather_app.data.room.entities.ForecastDayEntityConstants.DAY
import com.android.weather_app.data.room.entities.ForecastDayEntityConstants.HOUR
import com.android.weather_app.domain.model.ForecastDay

data class ForecastDayEntity(
    @ColumnInfo(name = DAY)
    val forecastDays: ForecastDaysEntity,

    @ColumnInfo(name = HOUR)
    val forecastDayHourList: List<ForecastDayHourListEntity>,
)

fun ForecastDay.toEntity() = ForecastDayEntity(
    forecastDays = forecastDays.toEntity(),
    forecastDayHourList = forecastDayHourList.map { it.toEntity() }
)

fun ForecastDayEntity.toDomain() = ForecastDay(
    forecastDays = forecastDays.toDomain(),
    forecastDayHourList = forecastDayHourList.map { it.toDomain() }
)


object ForecastDayEntityConstants {
    const val DAY: String = "day"
    const val HOUR: String = "hour"
}
