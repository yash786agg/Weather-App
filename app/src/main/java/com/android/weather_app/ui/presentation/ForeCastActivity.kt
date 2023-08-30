package com.android.weather_app.ui.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.work.BackoffPolicy
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.android.weather_app.common.Constants.WORK_MANAGER_REQUEST_NAME
import com.android.weather_app.ui.theme.WeatherAppTheme
import com.android.weather_app.workermanager.CustomWorker
import dagger.hilt.android.AndroidEntryPoint
import java.time.Duration
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class ForeCastActivity : ComponentActivity() {
    private val forecastVM: ForecastVM by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            forecastVM.fetchForeCastData()
            WeatherAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ForeCastScreenAPICall(viewState = forecastVM.state.value)
                }

                LaunchedEffect(key1 = Unit) {
                    val constraints = Constraints.Builder()
                      .setRequiredNetworkType(NetworkType.CONNECTED)
                      .build()

                    val workerRequest = PeriodicWorkRequestBuilder<CustomWorker>(
                        repeatInterval = 15,//900L, // 15 minutes
                        repeatIntervalTimeUnit = TimeUnit.SECONDS
                    ).setBackoffCriteria(
                        backoffPolicy = BackoffPolicy.LINEAR,
                        duration = Duration.ofSeconds(15)
                    ).setConstraints(constraints)
                        .build()

                    val workManager = WorkManager.getInstance(applicationContext)
                    workManager.enqueueUniquePeriodicWork(
                        WORK_MANAGER_REQUEST_NAME,
                        ExistingPeriodicWorkPolicy.KEEP,
                        workerRequest
                    )
                }
            }
        }
    }
}
