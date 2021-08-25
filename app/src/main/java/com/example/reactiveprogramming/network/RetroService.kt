package com.example.reactiveprogramming.network

import com.example.reactiveprogramming.model.RecyclerListData
import retrofit2.http.GET
import retrofit2.http.Query


//This is the RetroService Interface to get pokemon from the api
interface RetroService {

    //    @GET("pokemon?limit=11188&offset=0")
    @GET("pokemon")

    //This function queries our Api and returns the data class
//    suspend fun getDataFromApi(@Query("q") query: String): RecyclerListData
    suspend fun getDataFromApi(@Query("offset") query: Int, @Query("limit") lim: Int): RecyclerListData

}