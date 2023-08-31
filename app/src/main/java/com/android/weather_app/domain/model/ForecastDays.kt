package com.android.weather_app.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ForecastDays(val maxTempCelsius: Double?,
                        val minTempCelsius: Double?,
                        val foreCastDayCondition: ForeCastDayCondition?): Parcelable
