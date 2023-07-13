package com.furkansemizoglu.weatherapp.viewmodel

import androidx.lifecycle.MutableLiveData
import com.furkansemizoglu.weatherapp.model.WeatherModel
import com.furkansemizoglu.weatherapp.service.WeatherAPIService
import io.reactivex.rxjava3.disposables.CompositeDisposable

class MainViewModel {

    private val weatherApiService = WeatherAPIService()
    private val disposable = CompositeDisposable()


    val weatherData = MutableLiveData<WeatherModel>()
    val weatherError = MutableLiveData<Boolean>()
    val weatherLoading = MutableLiveData<Boolean>()




}