package com.android.weather_app.domain.repository

import kotlinx.coroutines.flow.Flow

interface ForecastRepositoryImpl {

    suspend fun getForecastData(): Flow<Any?>
}