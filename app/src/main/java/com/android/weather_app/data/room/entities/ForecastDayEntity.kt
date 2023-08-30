package com.android.weather_app.data.room.entities

import androidx.room.ColumnInfo
import com.android.weather_app.data.room.entities.ForecastDayEntityConstants.CHANCE_OF_RAIN
import com.android.weather_app.data.room.entities.ForecastDayEntityConstants.DATE
import com.android.weather_app.data.room.entities.ForecastDayEntityConstants.DAY
import com.android.weather_app.data.room.entities.ForecastDayEntityConstants.HOUR
import com.android.weather_app.domain.model.ForecastDay

data class ForecastDayEntity(
    @ColumnInfo(name = DATE)
    val forecastDate: String,

    @ColumnInfo(name = CHANCE_OF_RAIN)
    val forecastOfRain: Int,

    @ColumnInfo(name = DAY)
    val forecastDays: ForecastDaysEntity,

    @ColumnInfo(name = HOUR)
    val forecastDayHourList: List<ForecastDayHourListEntity>,
)

fun ForecastDay.toEntity() = ForecastDayEntity(
    forecastDate = forecastDate,
    forecastOfRain = forecastOfRain,
    forecastDays = forecastDays.toEntity(),
    forecastDayHourList = forecastDayHourList.map { it.toEntity() }
)

fun ForecastDayEntity.toDomain() = ForecastDay(
    forecastDate = forecastDate,
    forecastOfRain = forecastOfRain,
    forecastDays = forecastDays.toDomain(),
    forecastDayHourList = forecastDayHourList.map { it.toDomain() }
)


object ForecastDayEntityConstants {
    const val DATE: String = "date"
    const val CHANCE_OF_RAIN: String = "daily_chance_of_rain"
    const val DAY: String = "day"
    const val HOUR: String = "hour"
}
