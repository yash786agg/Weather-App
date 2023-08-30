package com.android.weather_app.data.di

import com.android.weather_app.data.network.api.WeatherAPIService
import com.android.weather_app.data.repository.ForecastRepository
import com.android.weather_app.data.room.db.WeatherDao
import com.android.weather_app.domain.repository.ForecastRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideForecastRepository(
        weatherDao: WeatherDao,
        weatherApi: WeatherAPIService
    ): ForecastRepositoryImpl {
        return ForecastRepository(weatherDao, weatherApi)
    }
}