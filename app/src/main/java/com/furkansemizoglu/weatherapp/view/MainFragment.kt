package com.furkansemizoglu.weatherapp.view

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.furkansemizoglu.weatherapp.R
import com.furkansemizoglu.weatherapp.databinding.FragmentMainBinding
import com.furkansemizoglu.weatherapp.viewmodel.MainViewModel


class MainFragment : Fragment() {

    private lateinit var binding : FragmentMainBinding
    private lateinit var viewModel : MainViewModel


    private lateinit var GET : SharedPreferences
    private lateinit var SET : SharedPreferences.Editor

    private lateinit var cName : String

    val PREFS_FILENAME = "com.furkansemizoglu.weatherapp"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentMainBinding.inflate(layoutInflater)

      //  GET  = this.requireActivity().getSharedPreferences()
        GET = this.requireActivity()
            .getSharedPreferences(PREFS_FILENAME, MODE_PRIVATE)

        SET = GET.edit()

        cName = GET.getString("cityName","Samsun").toString()
       // val city = "ankara"
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.refreshData(cName)



        binding.searchAreaView.setText(cName)
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


        binding.swipeRefreshLayout.setOnRefreshListener {
         //   binding.mainAreaLayout.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE
            binding.errorTextView.visibility = View.GONE
            binding.swipeRefreshLayout.isRefreshing = false
            viewModel.refreshData(cName)
            observeLiveData()
        }

        binding.searchButton.setOnClickListener {
            cName = binding.searchAreaView.text.toString()
            viewModel.refreshData(cName)
            observeLiveData()
        }
/*
        binding.searchAreaView.setOnQueryTextFocusChangeListener { v, hasFocus ->
            Toast.makeText(activity,"workingg differnet",Toast.LENGTH_LONG).show()
            val cityName =  binding.searchAreaView.query.toString()
            viewModel.refreshData(cityName)
            observeLiveData()
        }

        binding.searchAreaView.setOnSearchClickListener {
            Toast.makeText(activity,"workingg",Toast.LENGTH_LONG).show()
            val cityName =  binding.searchAreaView.query.toString()
            viewModel.refreshData(cityName)
            observeLiveData()
        }

 */



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

                Glide
                    .with(this)
                    .load("https://openweathermap.org/img/wn/" + it.weather[0].icon + "@2x.png")
                    .placeholder(R.mipmap.ic_launcher_round)
                    .into(binding.weatherIcon)
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

        viewModel.weatherError.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it){
                    binding.errorTextView.visibility = View.VISIBLE
                    binding.progressBar.visibility = View.GONE
                    binding.linearLayout.visibility = View.GONE
                }

            }
        })





    }
}