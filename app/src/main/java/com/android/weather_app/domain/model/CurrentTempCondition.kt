package com.android.weather_app.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CurrentTempCondition(
    val conditionText: String?,
    val conditionIcon: String?,
): Parcelable
