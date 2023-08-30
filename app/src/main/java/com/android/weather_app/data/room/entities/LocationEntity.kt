package com.android.weather_app.data.room.entities

import androidx.room.ColumnInfo
import com.android.weather_app.data.room.entities.LocationEntityConstants.LOCALTIME
import com.android.weather_app.data.room.entities.LocationEntityConstants.NAME
import com.android.weather_app.domain.model.Location

data class LocationEntity(
    @ColumnInfo(name = NAME)
    val name: String,

    @ColumnInfo(name = LOCALTIME)
    val localTime: String,
)

fun Location.toEntity() = LocationEntity(
    name = name,
    localTime = localTime,
)

fun LocationEntity.toDomain() = Location(
    name = name,
    localTime = localTime,
)

object LocationEntityConstants {
    //ColumnInfo FIELDS
    const val NAME: String = "name"
    const val LOCALTIME: String = "localtime"
}