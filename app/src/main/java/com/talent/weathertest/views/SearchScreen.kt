package com.talent.weathertest.views

import androidx.compose.runtime.*
import androidx.compose.material.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.Image
import coil.compose.rememberImagePainter
import androidx.compose.ui.text.input.TextFieldValue
import com.talent.weathertest.viewmodel.WeatherViewModel

@Composable
fun SearchScreen(viewModel: WeatherViewModel) {
    var city by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = city,
            onValueChange = { city = it },
            label = { Text("Enter City") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            viewModel.fetchWeather(city.text, "e5c385d1592c72bfb7ee7d070dfa3aa1\n")
        }) {
            Text("Get Weather")
        }

        viewModel.weatherData?.let { weather ->
            Spacer(modifier = Modifier.height(16.dp))
            Text("City: ${weather.name}")
            Text("Temperature: ${weather.main.temp} °C")
            Text("Description: ${weather.weather[0].description}")
            Image(
                painter = rememberImagePainter(
                    data = "http://openweathermap.org/img/wn/${weather.weather[0].icon}@2x.png"
                ),
                contentDescription = "Weather Icon"
            )
        }
    }
}