package com.furkansemizoglu.weatherapp.service

import com.furkansemizoglu.weatherapp.model.WeatherModel
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface WeatherAPI {

    //API_URL = "https://api.openweathermap.org/data/2.5/weather?lat=44.34&lon=10.99&appid=846c7a6fe27b1506a3739fd902351778"


    @GET("data/2.5/weather?lat=44.34&lon=10.99&appid=846c7a6fe27b1506a3739fd902351778")
    fun getWeatherData() : Single<WeatherModel>

}