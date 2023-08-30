package com.android.weather_app

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.android.weather_app.data.repository.ForecastRepository
import com.android.weather_app.workermanager.CustomWorker
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class WeatherApplication : Application(), Configuration.Provider {
    @Inject
    lateinit var workerFactory: CustomWorkerFactory
    override fun getWorkManagerConfiguration() =
        Configuration.Builder()
            .setMinimumLoggingLevel(Log.DEBUG)
            .setWorkerFactory(workerFactory)
            .build()
}

class CustomWorkerFactory @Inject constructor(
    private val forecastRepository: ForecastRepository
) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker = CustomWorker(
        forecastRepository = forecastRepository,
        appContext = appContext,
        workerParams = workerParameters
    )

}