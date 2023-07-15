package com.furkansemizoglu.weatherapp.service

import com.furkansemizoglu.weatherapp.model.WeatherModel
import com.furkansemizoglu.weatherapp.utils.Constants
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
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
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
        .create(WeatherAPI::class.java)


    fun getData(cityName : String) : Single<WeatherModel>{
        return api.getWeatherData(cityName)
    }





}