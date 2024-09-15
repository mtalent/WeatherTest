package com.talent.weathertest.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.talent.weathertest.model.WeatherResponse
import com.talent.weathertest.network.ApiService
import com.talent.weathertest.repository.WeatherRepository
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {

    private val apiService = ApiService.create()
    private val repository = WeatherRepository(apiService)

    var weatherData by mutableStateOf<WeatherResponse?>(null)
        private set

    fun fetchWeather(city: String, apiKey: String) {
        viewModelScope.launch {
            try {
                val response = repository.getWeatherByCity(city, apiKey)
                weatherData = response
            } catch (e: Exception) {
                "Error fetching weather data: ${e.localizedMessage}"
            }
        }
    }
}