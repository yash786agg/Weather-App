package com.android.weather_app.data.room.entities

import androidx.room.ColumnInfo
import com.android.weather_app.data.room.entities.ForeCastDayConditionConstants.ICON_VALUE
import com.android.weather_app.data.room.entities.ForeCastDayConditionConstants.TEXT_VALUE
import com.android.weather_app.domain.model.ForeCastDayCondition

data class ForeCastDayConditionEntity(
    @ColumnInfo(name = TEXT_VALUE)
    val conditionText: String?,

    @ColumnInfo(name = ICON_VALUE)
    val conditionIcon: String?,
)

fun ForeCastDayCondition.toEntity() = ForeCastDayConditionEntity(
    conditionText = conditionText,
    conditionIcon = conditionIcon
)

fun ForeCastDayConditionEntity.toDomain() = ForeCastDayCondition(
    conditionText = conditionText,
    conditionIcon = conditionIcon
)

object ForeCastDayConditionConstants {
    const val TEXT_VALUE: String = "text"
    const val ICON_VALUE: String = "icon"
}

