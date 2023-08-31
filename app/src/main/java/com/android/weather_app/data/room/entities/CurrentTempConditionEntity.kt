package com.android.weather_app.data.room.entities

import androidx.room.ColumnInfo
import com.android.weather_app.data.room.entities.CurrentTempConditionEntityConstants.ICON_VALUE
import com.android.weather_app.data.room.entities.CurrentTempConditionEntityConstants.TEXT_VALUE
import com.android.weather_app.domain.model.CurrentTempCondition

data class CurrentTempConditionEntity(
    @ColumnInfo(name = TEXT_VALUE)
    val conditionText: String?,

    @ColumnInfo(name = ICON_VALUE)
    val conditionIcon: String?,
)


fun CurrentTempCondition.toEntity() = CurrentTempConditionEntity(
    conditionText = conditionText,
    conditionIcon = conditionIcon
)

fun CurrentTempConditionEntity.toDomain() = CurrentTempCondition(
    conditionText = conditionText,
    conditionIcon = conditionIcon
)

object CurrentTempConditionEntityConstants {
    const val TEXT_VALUE: String = "text"
    const val ICON_VALUE: String = "icon"
}
