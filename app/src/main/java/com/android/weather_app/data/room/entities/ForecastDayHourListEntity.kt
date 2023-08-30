package com.android.weather_app.data.room.entities

import androidx.room.ColumnInfo
import com.android.weather_app.data.room.entities.ForecastDayHourListEntityConstants.FORECAST_DAY_CONDITION
import com.android.weather_app.data.room.entities.ForecastDayHourListEntityConstants.TEMP_CELSIUS
import com.android.weather_app.data.room.entities.ForecastDayHourListEntityConstants.TIME
import com.android.weather_app.domain.model.ForecastDayHourList

data class ForecastDayHourListEntity(
    @ColumnInfo(name = TIME)
    val time: String,

    @ColumnInfo(name = TEMP_CELSIUS)
    val foreCastDayTemp: Double,

    @ColumnInfo(name = FORECAST_DAY_CONDITION)
    val foreCastDayCondition: ForeCastDayConditionEntity,
)

fun ForecastDayHourList.toEntity() = ForecastDayHourListEntity(
    time = time,
    foreCastDayTemp = foreCastDayTemp,
    foreCastDayCondition = foreCastDayCondition.toEntity()
)

fun ForecastDayHourListEntity.toDomain() = ForecastDayHourList(
    time = time,
    foreCastDayTemp = foreCastDayTemp,
    foreCastDayCondition = foreCastDayCondition.toDomain(),
)


object ForecastDayHourListEntityConstants {
    const val TIME: String = "time"
    const val TEMP_CELSIUS: String = "temp_c"
    const val FORECAST_DAY_CONDITION: String = "condition"
}