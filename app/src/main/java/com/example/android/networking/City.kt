package com.example.android.networking

import com.google.gson.annotations.SerializedName
import java.nio.file.WatchEvent

class City {
    var title: String = " "
    var timezone: String = ""
    var time: String = ""
    @SerializedName("consolidated_weather")
    var weather: List<WeatherUpdate> = listOf()

    constructor ()
}