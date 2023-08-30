package com.android.weather_app.data.room.typeconverters

import androidx.room.TypeConverter
import com.android.weather_app.data.room.entities.ForecastEntity
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class ForecastConverter {
    private val adapter by lazy {
        val moshi = Moshi.Builder()
            .build()
        val listMyData = Types.newParameterizedType(ForecastEntity::class.java)
        return@lazy moshi.adapter<ForecastEntity>(listMyData)
    }

    @TypeConverter
    fun toJson(coordinates: ForecastEntity): String {
        return adapter.toJson(coordinates)
    }

    @TypeConverter
    fun fromJson(json: String): ForecastEntity? {
        return adapter.fromJson(json)
    }
}