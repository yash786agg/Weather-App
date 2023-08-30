package com.android.weather_app.data.repository

import android.util.Log
import com.android.weather_app.data.network.api.WeatherAPIService
import com.android.weather_app.data.network.model.NetworkResult
import com.android.weather_app.data.network.model.toDomain
import com.android.weather_app.data.room.db.WeatherDao
import com.android.weather_app.data.room.entities.toDomain
import com.android.weather_app.data.room.entities.toEntity
import com.android.weather_app.domain.repository.ForecastRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class ForecastRepository @Inject
constructor(
    private val weatherDao: WeatherDao,
    private val weatherApi: WeatherAPIService
) : ForecastRepositoryImpl {
    override suspend fun getForecastData() = flow {
        returnCachedData().let { cacheData ->
            Log.e("ForeCast", "fetchForeCastData cacheData: $cacheData")
            if (cacheData == null) {
                val response = weatherApi.getForecastDataAsync()
                if (response.isSuccessful && response.code() == 200) {
                    val forecastData = response.body()
                    forecastData?.let { weatherDto ->
                        val data = weatherDto.toDomain()
                        weatherDao.nukeTable()
                        weatherDao.insert(data.toEntity())
                        emit(returnCachedData())
                    }
                } else
                    emit(emitError(response.message()))
            } else {
                emit(cacheData)
            }
        }
    }.flowOn(Dispatchers.IO)

    private suspend fun returnCachedData(): Any? {
        weatherDao.getAll()?.let { data ->
            return NetworkResult.Success(data.toDomain())
        }
        return null
    }

    private fun emitError(error: String): Any {
        return NetworkResult.Error(errorMsg = error)
    }
}