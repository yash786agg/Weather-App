package com.android.weather_app.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Weather(
    val location: Location,
    val currentTemp: CurrentTemp,
    val forecast: Forecast
) : Parcelable
