package com.android.weather_app.common

import java.text.SimpleDateFormat
import java.util.Locale

object Utility {

    fun convertDateTimeFormat(inputDateTime: String?): String? {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
        val outputFormat = SimpleDateFormat("EEEE, d MMM yyyy", Locale.getDefault())

        val date = inputDateTime?.let { inputFormat.parse(it) }
        return date?.let { outputFormat.format(it) }
    }

    fun convertTo12HourFormat(inputDateTime: String?): String? {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
        val outputFormat = SimpleDateFormat("h a", Locale.getDefault())

        val date = inputDateTime?.let { inputFormat.parse(it) }
        return date?.let { outputFormat.format(it) }
    }

    fun getDayOfWeekFromDate(dateString: String?): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = dateString?.let { inputFormat.parse(it) }

        val dayOfWeekFormat = SimpleDateFormat("EEEE", Locale.getDefault())
        return dayOfWeekFormat.format(date!!)
    }

}