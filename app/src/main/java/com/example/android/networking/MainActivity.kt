package com.example.android.networking

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = Retrofit.Builder()
            .baseUrl(Consts.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


        val weatherInterface = retrofit.create(WeatherInterface::class.java)
        val id = intent.getIntExtra(Consts.CITY_ID,-1)
        if (id!=-1) {
            weatherInterface.getCityInfo(id)
                .enqueue(object : Callback<City> {

                    override fun onFailure(call: Call<City>, t: Throwable) {
                        Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_LONG).show()
                    }

                    override fun onResponse(call: Call<City>, response: Response<City>) {
                        val city = response.body()
                        if (city != null) {
                            titleTextView.text = city.title
                            timezoneTextView.text = city.timezone
                            timeTextView.text = city.time
                            weatherTextView.text = city.weather[0].theTemp.toString()
                        }
//                    titleTextView.text= response.body()?.title?:"unknown"
//                    timezoneTextView.text= response.body()?.timezone?:"unknown"
//                    timeTextView.text= response.body()?.time ?:"unknown"


                    }

                })
        }

    }
}

