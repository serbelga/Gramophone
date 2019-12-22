package com.android.sergiobelda.gramophone.repository

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LastFmRetrofitClient {
    val lastFmWebservice by lazy {
        Retrofit.Builder()
            .baseUrl("http://ws.audioscrobbler.com/2.0/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(LastFmWebservice::class.java)
    }
}