package com.android.weather_app

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.work.Configuration
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.android.weather_app.data.network.api.WeatherAPIService
import com.android.weather_app.data.room.db.WeatherDao
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
    private val weatherDao: WeatherDao,
    private val weatherApi: WeatherAPIService,
) : WorkerFactory() {
    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker = CustomWorker(
        weatherDao = weatherDao,
        weatherApi = weatherApi,
        appContext = appContext,
        workerParams = workerParameters
    )

}