package com.android.weather_app.data.network.model

import com.android.weather_app.data.network.model.ForecastDayHourListDtoConstant.FORECAST_DAY_CONDITION
import com.android.weather_app.data.network.model.ForecastDayHourListDtoConstant.TEMP_CELSIUS
import com.android.weather_app.data.network.model.ForecastDayHourListDtoConstant.TIME
import com.android.weather_app.domain.model.ForecastDayHourList
import com.squareup.moshi.Json

data class ForecastDayHourListDto(
    @field:Json(name = TIME)
    val time: String?,

    @field:Json(name = TEMP_CELSIUS)
    val foreCastDayTemp: Double?,

    @field:Json(name = FORECAST_DAY_CONDITION)
    val foreCastDayCondition: ForeCastDayConditionDto?,
)

fun ForecastDayHourList.toDto() = ForecastDayHourListDto(
    time = time,
    foreCastDayTemp = foreCastDayTemp,
    foreCastDayCondition = foreCastDayCondition?.toDto()
)

fun ForecastDayHourListDto.toDomain() = ForecastDayHourList(
    time = time,
    foreCastDayTemp = foreCastDayTemp,
    foreCastDayCondition = foreCastDayCondition?.toDomain(),
)


object ForecastDayHourListDtoConstant {
    const val TIME: String = "time"
    const val TEMP_CELSIUS: String = "temp_c"
    const val FORECAST_DAY_CONDITION: String = "condition"
}