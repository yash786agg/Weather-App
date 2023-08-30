package com.android.weather_app.data.network.model

import com.android.weather_app.data.network.model.CurrentTempDtoConstant.CURRENT_TEMP_CONDITION
import com.android.weather_app.data.network.model.CurrentTempDtoConstant.FEELS_LIKE
import com.android.weather_app.data.network.model.CurrentTempDtoConstant.TEMP_CELSIUS
import com.android.weather_app.data.room.entities.toDomain
import com.android.weather_app.domain.model.CurrentTemp
import com.squareup.moshi.Json

data class CurrentTempDto(
    @field:Json(name = TEMP_CELSIUS)
    val tempCelsius: Double,

    @field:Json(name = CURRENT_TEMP_CONDITION)
    val latestValue: CurrentTempConditionDto,

    @field:Json(name = FEELS_LIKE)
    val feelsLikeTemp: Double
)

fun CurrentTemp.toDto() = CurrentTempDto(
    tempCelsius = tempCelsius,
    latestValue = latestValue.toDto(),
    feelsLikeTemp = feelsLikeTemp
)

fun CurrentTempDto.toDomain() = CurrentTemp(
    tempCelsius = tempCelsius,
    latestValue = latestValue.toDomain(),
    feelsLikeTemp = feelsLikeTemp
)

object CurrentTempDtoConstant {
    const val TEMP_CELSIUS: String = "temp_c"

    const val CURRENT_TEMP_CONDITION: String = "condition"

    const val FEELS_LIKE: String = "feelslike_c"
}
