package com.android.weather_app.data.di

import com.android.weather_app.common.Constants.API_URL
import com.android.weather_app.common.Constants.TIMEOUT_REQUEST
import com.android.weather_app.data.network.api.WeatherAPIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun providesRetrofitBuilder(httpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(API_URL)
            .client(httpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun provideWeatherApiService(retrofit: Retrofit): WeatherAPIService =
        retrofit.create(WeatherAPIService::class.java)

    @Singleton
    @Provides
    fun httpClient(): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(TIMEOUT_REQUEST, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT_REQUEST, TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT_REQUEST, TimeUnit.SECONDS)
        .build()
}