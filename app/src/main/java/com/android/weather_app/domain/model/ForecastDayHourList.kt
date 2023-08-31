package com.android.weather_app.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ForecastDayHourList(
    val time: String?,
    val foreCastDayTemp: Double?,
    val foreCastDayCondition: ForeCastDayCondition?,
) : Parcelable
