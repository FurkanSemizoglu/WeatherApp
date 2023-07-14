package com.furkansemizoglu.weatherapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.furkansemizoglu.weatherapp.R
import com.furkansemizoglu.weatherapp.databinding.FragmentMainBinding
import com.furkansemizoglu.weatherapp.viewmodel.MainViewModel


class MainFragment : Fragment() {

    private lateinit var binding : FragmentMainBinding
    private lateinit var viewModel : MainViewModel




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentMainBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.refreshData()

        observeLiveData()
    }


    fun observeLiveData(){
        viewModel.weatherData.observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.linearLayout.visibility = View.VISIBLE
                binding.countryCode.text = it.sys.country.toString()
                binding.countryName.text = it.name.toString()
                binding.weatherDegree.text = it.main.temp.toString()
                binding.feelsLike.text = it.main.feels_like.toString()
                binding.windSpeed.text = it.wind.speed.toString()
                binding.weatherTemp.text = it.main.temp.toString()
                binding.weatherDescription.text = it.weather[0].description.toString()
            }
        })

        viewModel.weatherLoading.observe(viewLifecycleOwner, Observer {
            it?.let {
                if(!it){
                    binding.progressBar.visibility = View.GONE
                }
                else{
                    binding.errorTextView.visibility = View.GONE
                    binding.progressBar.visibility = View.VISIBLE
                    binding.linearLayout.visibility = View.GONE
                }


            }
        })
/*
        viewModel.weatherError.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it){
                    binding.errorTextView.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                    binding.linearLayout.visibility = View.GONE
                }

            }
        })*/

        viewModel.weatherError.observe()
    }
}