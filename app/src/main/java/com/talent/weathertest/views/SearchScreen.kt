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
import androidx.compose.ui.unit.sp
import com.talent.weathertest.viewmodel.WeatherViewModel

@Composable
fun SearchScreen(viewModel: WeatherViewModel) {
    var city by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp) // Horizontal padding for the entire column
            .padding(top = 64.dp), // Move the content down from the top
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Input field for city search
        TextField(
            value = city,
            onValueChange = { city = it },
            label = { Text("Enter City") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp) // Space between input and button
        )

        // Search button
        Button(
            onClick = { viewModel.fetchWeather(city.text, "0416d64d47a0d891f5f0f97d4d974d28") },
            modifier = Modifier
                .padding(bottom = 32.dp) // Extra space between button and weather info
        ) {
            Text("Get Weather", fontSize = 20.sp) // Increase button text size
        }

        // Weather info displayed after the city is searched
        viewModel.weatherData?.let { weather ->
            Spacer(modifier = Modifier.height(16.dp))

            // City name
            Text(
                text = "City: ${weather.name}",
                fontSize = 32.sp, // Increase text size
                modifier = Modifier.padding(8.dp)
            )

            // Temperature
            Text(
                text = "Temperature: ${weather.main.temp} °C",
                fontSize = 28.sp, // Increase text size
                modifier = Modifier.padding(8.dp)
            )

            // Weather description
            Text(
                text = "Description: ${weather.weather[0].description}",
                fontSize = 28.sp, // Increase text size
                modifier = Modifier.padding(8.dp)
            )

            // Weather icon
            Image(
                painter = rememberImagePainter(
                    data = "http://openweathermap.org/img/wn/${weather.weather[0].icon}@2x.png"
                ),
                contentDescription = "Weather Icon",
                modifier = Modifier
                    .size(100.dp) // Adjust icon size
                    .padding(16.dp) // Space around the icon
            )
        }
    }
}
