package com.example.reactiveprogramming.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroInstance {

    //The retro instance class for the base url is created
    companion object {
        val BaseURL = "https://pokeapi.co/api/v2/"

        fun getRetroInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BaseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        }

    }
}