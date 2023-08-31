package com.android.weather_app.data.network.model

import com.android.weather_app.data.network.model.CurrentTempConditionDtoConstant.ICON_VALUE
import com.android.weather_app.data.network.model.CurrentTempConditionDtoConstant.TEXT_VALUE
import com.android.weather_app.domain.model.CurrentTempCondition
import com.squareup.moshi.Json

data class CurrentTempConditionDto(
    @field:Json(name = TEXT_VALUE)
    val conditionText: String?,

    @field:Json(name = ICON_VALUE)
    val conditionIcon: String?,
)

fun CurrentTempCondition.toDto() = CurrentTempConditionDto(
    conditionText = conditionText,
    conditionIcon = conditionIcon
)

fun CurrentTempConditionDto.toDomain() = CurrentTempCondition(
    conditionText = conditionText,
    conditionIcon = conditionIcon
)

object CurrentTempConditionDtoConstant {
    const val TEXT_VALUE: String = "text"
    const val ICON_VALUE: String = "icon"
}