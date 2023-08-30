package com.android.weather_app.data.network.model

import com.android.weather_app.data.network.model.LocationDtoConstant.LOCALTIME
import com.android.weather_app.data.network.model.LocationDtoConstant.NAME
import com.android.weather_app.domain.model.Location
import com.squareup.moshi.Json

data class LocationDto(
    @field:Json(name = NAME)
    val name: String,
    @field:Json(name = LOCALTIME)
    val localTime: String,
)

fun Location.toDto() = LocationDto(
    name = name,
    localTime = localTime,
)

fun LocationDto.toDomain() = Location(
    name = name,
    localTime = localTime,
)

object LocationDtoConstant {
    //ColumnInfo FIELDS
    const val NAME: String = "name"
    const val LOCALTIME: String = "localtime"
}