package com.android.weather_app.data.room.entities

import androidx.room.ColumnInfo
import com.android.weather_app.data.room.entities.CurrentTempEntityConstants.CURRENT_TEMP_CONDITION
import com.android.weather_app.data.room.entities.CurrentTempEntityConstants.FEELS_LIKE
import com.android.weather_app.data.room.entities.CurrentTempEntityConstants.TEMP_CELSIUS
import com.android.weather_app.domain.model.CurrentTemp

data class CurrentTempEntity(
    @ColumnInfo(name = TEMP_CELSIUS)
    val tempCelsius: Double?,

    @ColumnInfo(name = CURRENT_TEMP_CONDITION)
    val latestValue: CurrentTempConditionEntity?,

    @ColumnInfo(name = FEELS_LIKE)
    val feelsLikeTemp: Double?
)

fun CurrentTemp.toEntity() = CurrentTempEntity(
    tempCelsius = tempCelsius,
    latestValue = latestValue?.toEntity(),
    feelsLikeTemp = feelsLikeTemp
)

fun CurrentTempEntity.toDomain() = CurrentTemp(
    tempCelsius = tempCelsius,
    latestValue = latestValue?.toDomain(),
    feelsLikeTemp = feelsLikeTemp
)

object CurrentTempEntityConstants {

    const val TEMP_CELSIUS: String = "temp_c"

    const val CURRENT_TEMP_CONDITION: String = "condition"

    const val FEELS_LIKE: String = "feelslike_c"
}