package com.example.reactiveprogramming.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reactiveprogramming.model.RecyclerListData
import com.example.reactiveprogramming.network.RetroInstance
import com.example.reactiveprogramming.network.RetroService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.UnknownHostException

//This is the MainActivity View Model class
class MainActivityViewModel : ViewModel() {

    // Holds the details of the pokemon retrieved from the API
    lateinit var recyclerListLiveData: MutableLiveData<RecyclerListData>


    init {
        recyclerListLiveData = MutableLiveData()

    }

    //This function returns the recyclerListLiveData
    fun getRecyclerListObserver(): MutableLiveData<RecyclerListData> {
        return recyclerListLiveData
    }

    //This function is responsible of making Api call to our Api server in IO instead of main thread
    fun makeApiCall(idd: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
                val response = retroInstance.getDataFromApi(0, idd)
                recyclerListLiveData.postValue(response)
            }catch (e: UnknownHostException){
                e.printStackTrace()
            }


        }
    }
}