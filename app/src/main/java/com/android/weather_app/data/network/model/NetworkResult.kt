package com.android.weather_app.data.network.model

sealed class NetworkResult<out T: Any> {
    data class Loading(val show: Boolean = false) : NetworkResult<Nothing>()
    data class Success<out T : Any>(val data: T) : NetworkResult<T>()
    data class Error(val errorMsg: String) : NetworkResult<Nothing>()
}
