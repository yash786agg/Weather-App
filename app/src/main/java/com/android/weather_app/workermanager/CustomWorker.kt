package com.android.weather_app.workermanager

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.work.WorkerParameters
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import com.android.weather_app.common.InternetConnection.checkConnection
import com.android.weather_app.data.network.api.WeatherAPIService
import com.android.weather_app.data.network.model.toDomain
import com.android.weather_app.data.room.db.WeatherDao
import com.android.weather_app.data.room.entities.toEntity
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.net.UnknownHostException

@HiltWorker
class CustomWorker @AssistedInject constructor(
    @Assisted private val weatherDao: WeatherDao,
    @Assisted private val weatherApi: WeatherAPIService,
    @Assisted private val appContext: Context,
    @Assisted workerParams: WorkerParameters,
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        // Reference:- https://youtu.be/O9_RSYSmeIE
        Toast.makeText(applicationContext,"CustomWorker doWork API request triggered",Toast.LENGTH_LONG).show()
        try {
            Log.e("ForeCast", "CustomWorker doWork API request triggered")
            if (checkConnection(appContext)){
                val response = weatherApi.getForecastDataAsync()
                if (response.isSuccessful && response.code() == 200) {
                    Log.e("ForeCast", "CustomWorker doWork API request triggered success:- ${response.isSuccessful}")
                    val forecastData = response.body()
                    forecastData?.let { weatherDto ->
                        val data = weatherDto.toDomain()
                        weatherDao.nukeTable()
                        weatherDao.insert(data.toEntity())
                    }
                    Result.success()
                } else
                    Result.retry()
            } else
                Result.retry()
        } catch (e: Exception) {
            Log.e("ForeCast", "Error CustomWorker doWork API request triggered Error")
            if(e is UnknownHostException)
                Result.retry()
            else
                Result.failure()
        }
    }
}
