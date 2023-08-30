package com.android.weather_app.ui.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.weather_app.data.network.model.NetworkResult
import com.android.weather_app.data.repository.ForecastRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class ForecastVM @Inject constructor(
    private val forecastRepository: ForecastRepository
) : ViewModel() {

    fun fetchForeCastData() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                forecastRepository.getForecastData()
                    .catch {
                        withContext(Dispatchers.Main){
                            Log.e("ForeCast","fetchForeCastData error: ${it.message.toString()}")
                        }
                    }
                    .collect {response->
                        when (response) {
                            is NetworkResult<*> -> {
                                withContext(Dispatchers.Main){
                                    Log.e("ForeCast","fetchForeCastData response.isSuccessful: $response")
                                }
                            }
                        }
                    }
            }
        }
    }
}