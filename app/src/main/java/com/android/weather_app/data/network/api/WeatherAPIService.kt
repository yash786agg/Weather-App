package com.android.weather_app.data.network.api

import com.android.weather_app.common.Constants.ALERTS
import com.android.weather_app.common.Constants.AQI
import com.android.weather_app.common.Constants.FORECAST_API
import com.android.weather_app.common.Constants.WEATHER_API_KEY
import com.android.weather_app.common.Constants.WEATHER_API_KEY_TAG
import com.android.weather_app.common.Constants.CITY_QUERY
import com.android.weather_app.common.Constants.CITY_QUERY_TAG
import com.android.weather_app.common.Constants.DAYS
import com.android.weather_app.common.Constants.DAYS_TAG
import com.android.weather_app.common.Constants.NO
import com.android.weather_app.data.network.model.WeatherDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPIService {

    // @GET is the type of request
    @GET(FORECAST_API)
    suspend fun getForecastDataAsync(
        @Query(WEATHER_API_KEY_TAG) key: String = WEATHER_API_KEY,
        @Query(CITY_QUERY) city: String = CITY_QUERY_TAG,
        @Query(DAYS) days: Int = DAYS_TAG,
        @Query(AQI) aqi: String = NO,
        @Query(ALERTS) alerts: String = NO
    ) : Response<WeatherDto>
}