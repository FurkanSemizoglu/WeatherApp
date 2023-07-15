package com.furkansemizoglu.weatherapp.service

import com.furkansemizoglu.weatherapp.model.WeatherModel
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {


    //NEW_API_URL = "https://api.openweathermap.org/data/2.5/weather?q=samsun&appid=846c7a6fe27b1506a3739fd902351778"
    //API_URL = "https://api.openweathermap.org/data/2.5/weather?lat=44.34&lon=10.99&appid=846c7a6fe27b1506a3739fd902351778"


    @GET("data/2.5/weather?&appid=846c7a6fe27b1506a3739fd902351778")
    fun getWeatherData(
        @Query("q") cityName : String
    ) : Single<WeatherModel>

}