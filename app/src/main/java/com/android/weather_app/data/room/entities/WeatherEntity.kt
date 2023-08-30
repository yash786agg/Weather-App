package com.android.weather_app.data.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.android.weather_app.data.room.entities.WeatherEntityConstants.CURRENT_TEMP
import com.android.weather_app.data.room.entities.WeatherEntityConstants.FORECAST
import com.android.weather_app.data.room.entities.WeatherEntityConstants.ID
import com.android.weather_app.data.room.entities.WeatherEntityConstants.LOCATION
import com.android.weather_app.data.room.entities.WeatherEntityConstants.WEATHER_TABLE_NAME
import com.android.weather_app.domain.model.Weather

@Entity(tableName = WEATHER_TABLE_NAME)
data class WeatherEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    val id: Long,

    @ColumnInfo(name = LOCATION)
    val location: LocationEntity,

    @ColumnInfo(name = CURRENT_TEMP)
    val currentTemp: CurrentTempEntity,

    @ColumnInfo(name = FORECAST)
    val forecast: ForecastEntity,
)

fun Weather.toEntity() = WeatherEntity(
    id = 0,
    location = location.toEntity(),
    currentTemp = currentTemp.toEntity(),
    forecast = forecast.toEntity()
)

fun WeatherEntity.toDomain() = Weather(
    location = location.toDomain(),
    currentTemp = currentTemp.toDomain(),
    forecast = forecast.toDomain()
)

object WeatherEntityConstants {

    //table name
    const val WEATHER_TABLE_NAME: String = "weather"

    //ColumnInfo FIELDS
    const val ID: String = "id"
    const val LOCATION: String = "location"
    const val CURRENT_TEMP: String = "current"
    const val FORECAST: String = "forecast"
}