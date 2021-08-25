package com.example.reactiveprogramming.network

import com.example.reactiveprogramming.model.Abilities
import retrofit2.http.GET
import retrofit2.http.Path

//This is the RetroService Interface to get each pokeman full details
interface RetroServiceDetailsAPI {

    //This function queries our Api and returns the data class
    @GET("pokemon/{id}")
    suspend fun getPokemonDetails(@Path("id") query: String): Abilities
}