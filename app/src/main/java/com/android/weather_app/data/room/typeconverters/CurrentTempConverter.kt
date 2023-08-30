package com.android.weather_app.data.room.typeconverters

import androidx.room.TypeConverter
import com.android.weather_app.data.room.entities.CurrentTempEntity
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class CurrentTempConverter {
    private val adapter by lazy {
        val moshi = Moshi.Builder()
            .build()
        val listMyData = Types.newParameterizedType(CurrentTempEntity::class.java)
        return@lazy moshi.adapter<CurrentTempEntity>(listMyData)
    }

    @TypeConverter
    fun toJson(coordinates: CurrentTempEntity): String {
        return adapter.toJson(coordinates)
    }

    @TypeConverter
    fun fromJson(json: String): CurrentTempEntity? {
        return adapter.fromJson(json)
    }
}