package com.android.weather_app.data.di

import android.content.Context
import androidx.room.Room
import com.android.weather_app.data.room.db.WeatherDao
import com.android.weather_app.data.room.db.WeatherDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Singleton
    @Provides
    fun provideWeatherDatabase(
        @ApplicationContext context: Context
    ): WeatherDatabase {
        return Room
            .databaseBuilder(
                context,
                WeatherDatabase::class.java,
                WeatherDatabase.DATABASE_NAME
            )
            .build()
    }

    @Singleton
    @Provides
    fun provideWeatherDao(database: WeatherDatabase): WeatherDao {
        return database.WeatherDao()
    }
}