package com.android.weather_app.data.room.db

import androidx.room.Dao
import androidx.room.Query
import com.android.weather_app.data.room.entities.WeatherEntity

@Dao
abstract class WeatherDao : BaseDao<WeatherEntity> {
    @Query("delete from weather")
    suspend abstract fun nukeTable()

    @Query("select * from weather")
    suspend abstract fun getAll(): WeatherEntity?
}