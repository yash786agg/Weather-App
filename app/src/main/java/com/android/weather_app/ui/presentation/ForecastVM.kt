package com.android.weather_app.ui.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.weather_app.data.network.model.NetworkResult
import com.android.weather_app.data.repository.ForecastRepository
import com.android.weather_app.domain.model.Weather
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ForecastVM @Inject constructor(
    private val forecastRepository: ForecastRepository
) : ViewModel() {

    private  val _state = mutableStateOf<NetworkResult<Weather>>(NetworkResult.Loading())
    val state: State<NetworkResult<Weather>>
        get() = _state

    fun fetchForeCastData() {
        viewModelScope.launch {
            _state.value = NetworkResult.Loading(show = true)
            withContext(Dispatchers.IO) {
                forecastRepository.getForecastData()
                    .catch {
                        withContext(Dispatchers.Main) {
                            _state.value = NetworkResult.Error(it.message.toString())
                        }
                    }
                    .collect { response->
                        withContext(Dispatchers.Main) {
                            _state.value = response as NetworkResult<Weather>
                        }
                    }
            }
        }
    }
}