package com.talent.weathertest.repository

import com.talent.weathertest.model.WeatherResponse
import com.talent.weathertest.network.ApiService

class WeatherRepository(private val apiService: ApiService) {

    suspend fun getWeatherByCity(city: String, apiKey: String): WeatherResponse {
        return apiService.getWeatherByCity(city, apiKey)
    }
}