package com.android.weather_app.workermanager

import android.content.Context
import android.util.Log
import androidx.work.WorkerParameters
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import com.android.weather_app.data.repository.ForecastRepository
import com.android.weather_app.data.room.db.WeatherDao
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.withContext

@HiltWorker
class CustomWorker @AssistedInject constructor(
    @Assisted private val forecastRepository: ForecastRepository,
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
) : CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        // Reference:- https://youtu.be/O9_RSYSmeIE
        try {
            Log.e("ForeCast", "CustomWorker doWork API request triggered")
            forecastRepository.getForecastData()
                .catch {
                    Log.e("ForeCast", "CustomWorker catch: $it")
                }
                .collect {
                    Log.e("ForeCast", "CustomWorker collect: $it")
                }
            Result.success()
        } catch (e: Exception) {
            // Handle exceptions and return Result.failure() if needed
            Result.failure()
        }
    }
}
