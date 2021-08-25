package com.example.reactiveprogramming.viewmodel

import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reactiveprogramming.model.Abilities
import com.example.reactiveprogramming.model.RecyclerListData
import com.example.reactiveprogramming.network.RetroInstance
import com.example.reactiveprogramming.network.RetroService
import com.example.reactiveprogramming.network.RetroServiceDetailsAPI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PokemonDetailsViewModel: ViewModel() {

    // This variable Holds the details of the pokemon retrieved from the API
    lateinit var detailsLiveData: MutableLiveData<Abilities>

    init {
        detailsLiveData = MutableLiveData()

    }
    //This function returns the detailsLiveData
    fun getRecyclerListObserver2(): MutableLiveData<Abilities> {
        return detailsLiveData
    }


    //This function is responsible of making Api call to our Api server in IO instead of main thread
    fun makeApiCall2(id:String) {
        try {
            viewModelScope.launch(Dispatchers.IO) {
                val retroInstance = RetroInstance.getRetroInstance().create(RetroServiceDetailsAPI::class.java)
                val response = retroInstance.getPokemonDetails(id)
                detailsLiveData.postValue(response)
            }
        }catch (e: Exception){

        }

    }
}