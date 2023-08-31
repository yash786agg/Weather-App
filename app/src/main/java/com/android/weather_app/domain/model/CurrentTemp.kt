package com.android.weather_app.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CurrentTemp(
    val tempCelsius: Double?,
    val latestValue: CurrentTempCondition?,
    val feelsLikeTemp: Double?
): Parcelable
