package com.android.weather_app.data.room.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.android.weather_app.data.room.entities.WeatherEntity
import com.android.weather_app.data.room.typeconverters.CurrentTempConverter
import com.android.weather_app.data.room.typeconverters.ForecastConverter
import com.android.weather_app.data.room.typeconverters.LocationConverter

@Database(entities = [WeatherEntity::class], version = 1, exportSchema = false)
@TypeConverters(
    LocationConverter::class, CurrentTempConverter::class, ForecastConverter::class
)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun WeatherDao(): WeatherDao

    companion object{
        val DATABASE_NAME = "weather_db"
    }
}
