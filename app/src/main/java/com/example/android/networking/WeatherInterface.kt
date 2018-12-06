package com.example.android.networking

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface WeatherInterface {
    @GET ("location/{id}/")
    fun getCityInfo(@Path("id")id:Int): Call<City>

}