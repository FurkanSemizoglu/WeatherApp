package com.furkansemizoglu.weatherapp.viewmodel

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.furkansemizoglu.weatherapp.model.WeatherModel
import com.furkansemizoglu.weatherapp.service.WeatherAPI
import com.furkansemizoglu.weatherapp.service.WeatherAPIService
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.lang.Exception




class MainViewModel() : ViewModel(){

    private val weatherApiService = WeatherAPIService()
    private val disposable = CompositeDisposable()


    val weatherData = MutableLiveData<WeatherModel>()
    val weatherError = MutableLiveData<Boolean>()
    val weatherLoading = MutableLiveData<Boolean>()




    fun refreshData(cityName: String){
        getDataFromAPI(cityName)
    }

    fun getDataFromAPI(cityName : String){

        weatherLoading.value = true

        disposable.add(weatherApiService.getData(cityName)
            .subscribeOn(io.reactivex.rxjava3.schedulers.Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object :
                io.reactivex.rxjava3.observers.DisposableSingleObserver<WeatherModel>() {
                override fun onSuccess(t: WeatherModel) {
                    weatherError.value = false
                    weatherLoading.value = false
                    weatherData.value = t
                }

                override fun onError(e: Throwable) {
                    weatherError.value = true
                    weatherLoading.value = false
                    e.printStackTrace()
                }

            })

        )


    }

}