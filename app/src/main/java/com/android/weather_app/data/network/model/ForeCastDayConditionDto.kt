package com.android.weather_app.data.network.model

import com.android.weather_app.data.network.model.ForeCastDayConditionDtoConstant.ICON_VALUE
import com.android.weather_app.data.network.model.ForeCastDayConditionDtoConstant.TEXT_VALUE
import com.android.weather_app.domain.model.ForeCastDayCondition
import com.squareup.moshi.Json

data class ForeCastDayConditionDto(
    @field:Json(name = TEXT_VALUE)
    val conditionText: String,

    @field:Json(name = ICON_VALUE)
    val conditionIcon: String,
)

fun ForeCastDayCondition.toDto() = ForeCastDayConditionDto(
    conditionText = conditionText,
    conditionIcon = conditionIcon
)

fun ForeCastDayConditionDto.toDomain() = ForeCastDayCondition(
    conditionText = conditionText,
    conditionIcon = conditionIcon
)

object ForeCastDayConditionDtoConstant {
    const val TEXT_VALUE: String = "text"
    const val ICON_VALUE: String = "icon"
}
