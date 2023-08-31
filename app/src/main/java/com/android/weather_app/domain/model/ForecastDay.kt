package com.android.weather_app.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ForecastDay(
    val forecastDate: String?,
    val forecastDays: ForecastDays?,
    val forecastDayHourList: List<ForecastDayHourList>?
) : Parcelable
