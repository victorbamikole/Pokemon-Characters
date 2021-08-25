package com.example.reactiveprogramming.viewmodel

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.reactiveprogramming.R
import com.google.android.material.snackbar.Snackbar

object Method {

    //This function splits the base url and get the last index
    internal fun getIndexFromUrl(url: String): String {
        val index = url.split("/")
        val int = index.lastIndex
        return index.elementAt(int - 1)
    }

    //This function displays the snackbar
    fun View.snackbar(message: String){
        Snackbar.make(
            this,
            message,
            Snackbar.LENGTH_LONG
        ).also { snackbar ->
            snackbar.setAction("OK"){
                snackbar.dismiss()
            }
        }.show()
    }

}