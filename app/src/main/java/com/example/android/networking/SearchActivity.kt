package com.example.android.networking

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.text.Editable
import android.text.TextWatcher
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.item_city.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.temporal.TemporalQuery

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        searchQueryEditText.addTextChangedListener(object: TextWatcher {

            override fun afterTextChanged(s: Editable?) {

                searchCities(s.toString())

            }



            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }



            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {



            }



        })    }

        fun prepareRecyclerView(cityList: List<City>) {


            recyclerView.layoutManager = GridLayoutManager(this, 2)

            recyclerView.adapter = CityAdapter(cityList)
        }

    fun searchCities(searchQuery: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.metaweather.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        val weatherInterface = retrofit.create(WeatherInterface::class.java)

        weatherInterface.searchForCity(searchQuery)
            .enqueue(object : Callback<List<City>> {
                override fun onFailure(call: Call<List<City>>, t: Throwable) {
                }

                override fun onResponse(call: Call<List<City>>, response: Response<List<City>>) {
                    response.body()?.let { cityList  ->
                        prepareRecyclerView(cityList)
                    }
                }

            })
    }

}


