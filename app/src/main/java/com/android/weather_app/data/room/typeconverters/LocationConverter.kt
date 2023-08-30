package com.android.weather_app.data.room.typeconverters

import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.android.weather_app.data.room.entities.LocationEntity

class LocationConverter {
    private val adapter by lazy {
        val moshi = Moshi.Builder()
            .build()
        val listMyData = Types.newParameterizedType(LocationEntity::class.java)
        return@lazy moshi.adapter<LocationEntity>(listMyData)
    }

    @TypeConverter
    fun toJson(coordinates: LocationEntity): String {
        return adapter.toJson(coordinates)
    }

    @TypeConverter
    fun fromJson(json: String): LocationEntity? {
        return adapter.fromJson(json)
    }
}