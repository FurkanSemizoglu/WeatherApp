package com.furkansemizoglu.weatherapp.service

import com.furkansemizoglu.weatherapp.model.WeatherModel
import com.furkansemizoglu.weatherapp.utils.Constants
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class WeatherAPIService {


    private val BASE_URL = "https://api.openweathermap.org/"

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(WeatherAPI::class.java)


    private fun getData() : Single<WeatherModel>{
        return api.getWeatherData()
    }





}