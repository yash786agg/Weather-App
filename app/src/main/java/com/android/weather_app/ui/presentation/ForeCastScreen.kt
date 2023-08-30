package com.android.weather_app.ui.presentation

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.android.weather_app.common.Constants.DEGREE
import com.android.weather_app.common.Constants.DEGREE_CELSIUS
import com.android.weather_app.common.Constants.FEELS_LIKE
import com.android.weather_app.common.Constants.HTTPS_APPEND_TAG
import com.android.weather_app.common.Constants.PERCENT
import com.android.weather_app.common.Utility.convertDateTimeFormat
import com.android.weather_app.common.Utility.convertTo12HourFormat
import com.android.weather_app.common.Utility.getDayOfWeekFromDate
import com.android.weather_app.data.network.model.NetworkResult
import com.android.weather_app.domain.model.Forecast
import com.android.weather_app.domain.model.Weather

@Composable
fun ForeCastScreenAPICall(viewState: NetworkResult<Weather>) {
    viewState.let {
        when (viewState) {
            is NetworkResult.Loading -> {
                Log.e("ForeCast", "MainActivity Loading: ${viewState.show}")
                if(viewState.show)
                    ShowCircularProgressIndicator(show = true)
                else
                    ShowCircularProgressIndicator(show = false)
            }

            is NetworkResult.Error -> {
                val errorMsg = viewState.errorMsg
                Log.e("ForeCast", "MainActivity errorMsg: $errorMsg")
            }

            is NetworkResult.Success -> {
                val data = viewState.data
                Log.e("ForeCast", "MainActivity success Data: $data")
                ForeCastScreen(weatherData = data)
            }
        }
    }
}

@Composable
fun ForeCastScreen(weatherData: Weather) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        item {
            convertDateTimeFormat(weatherData.location.localTime)?.let { time ->
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    text = time,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = weatherData.location.name,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                Text(
                    modifier = Modifier.padding(top = 10.dp),
                    text = weatherData.currentTemp.latestValue.conditionText,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center
                )

                Image(
                    modifier = Modifier
                        .size(80.dp)
                        .padding(top = 10.dp),
                    painter = rememberAsyncImagePainter("${HTTPS_APPEND_TAG}${weatherData.currentTemp.latestValue.conditionIcon}"),
                    contentDescription = null,
                )

                Text(
                    modifier = Modifier.padding(top = 10.dp),
                    text = "${weatherData.currentTemp.tempCelsius.toInt()}$DEGREE_CELSIUS",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                Text(
                    modifier = Modifier.padding(top = 10.dp),
                    text = "$FEELS_LIKE ${weatherData.currentTemp.feelsLikeTemp.toInt()}$DEGREE_CELSIUS",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center
                )
            }
        }

        item {
            Spacer(modifier = Modifier.height(20.dp))
            TodayForecastContent(weatherData.forecast)
        }

        item {
            ForecastContent(weatherData.forecast)
        }
    }
}

@Composable
fun TodayForecastContent(forecast: Forecast) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        val forecastTodayData = forecast.foreCastDay[0].forecastDayHourList

        items(forecastTodayData.size) { item ->
            Column(
                modifier = Modifier
                    .width(50.dp)
                    .padding(5.dp)
            ) {
                convertTo12HourFormat(forecastTodayData[item].time)?.let { time ->
                    Text(
                        text = time,
                        fontSize = 10.sp,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }

                Image(
                    modifier = Modifier
                        .size(50.dp),
                    painter = rememberAsyncImagePainter("${HTTPS_APPEND_TAG}${forecastTodayData[item].foreCastDayCondition.conditionIcon}"),
                    contentDescription = null,
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "${forecastTodayData[item].foreCastDayTemp.toInt()}$DEGREE",
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}

@Composable
fun ForecastContent(forecast: Forecast) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
        ) {
            val forecastData = forecast.foreCastDay
            items(forecastData.size) { item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier.weight(2f),
                        text = getDayOfWeekFromDate(forecastData[item].forecastDate),
                        fontSize = 14.sp,
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Bold,
                    )

                    Text(
                        modifier = Modifier.weight(1f),
                        text = "${forecastData[item].forecastOfRain}$PERCENT",
                        fontSize = 14.sp,
                        textAlign = TextAlign.Start,
                        fontWeight = FontWeight.Bold,
                    )

                    Image(
                        modifier = Modifier
                            .size(50.dp)
                            .weight(1f),
                        painter = rememberAsyncImagePainter("${HTTPS_APPEND_TAG}${forecastData[item].forecastDays.foreCastDayCondition.conditionIcon}"),
                        contentDescription = null,
                    )

                    Text(
                        modifier = Modifier.weight(1f),
                        text = "${forecastData[item].forecastDays.minTempCelsius.toInt()}$DEGREE",
                        fontSize = 14.sp,
                        textAlign = TextAlign.End,
                        fontWeight = FontWeight.Bold,
                    )

                    Text(
                        modifier = Modifier.weight(1f),
                        text = "${forecastData[item].forecastDays.maxTempCelsius.toInt()}$DEGREE",
                        fontSize = 14.sp,
                        textAlign = TextAlign.End,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        }
    }
}

@Composable
private fun ShowCircularProgressIndicator(show: Boolean) {
    if(show){
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(
                color = Color.Black
            )
        }
    }
}