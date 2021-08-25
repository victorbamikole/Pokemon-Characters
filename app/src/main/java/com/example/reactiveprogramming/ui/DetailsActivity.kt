package com.example.reactiveprogramming.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.reactiveprogramming.R
import com.example.reactiveprogramming.connectivity.ConnectivityLiveData
import com.example.reactiveprogramming.model.Abilities
import com.example.reactiveprogramming.viewmodel.PokemonDetailsViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.fragment_recycler_list.*

class DetailsActivity : AppCompatActivity() {
    private lateinit var connectivityLiveData: ConnectivityLiveData
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        var backBtn = findViewById<ImageView>(R.id.backBtn)
        connectivityLiveData = ConnectivityLiveData(application)


        //The id of each item in RecyclerView list is gotten from RecyclerListFragment Activity
        var position = intent.getIntExtra("key", 1)
        val idNew = position + 1
        Toast.makeText(this, "${position + 1}", Toast.LENGTH_SHORT).show()

        backBtn.setOnClickListener {
            onBackPressed()
        }

        initViewModel(idNew.toString())
    }

    //This function binds the Details Data class too the layout ImageViews and TextViews
    private fun bindDetails(it: Abilities) {
        val pokename = findViewById<TextView>(R.id.pkname)
        val height = findViewById<TextView>(R.id.height)
        val weight = findViewById<TextView>(R.id.weight)
        val abilities = findViewById<TextView>(R.id.abilities)
        val bexp = findViewById<TextView>(R.id.bExp)

        val back_default = findViewById<ImageView>(R.id.pokeMini1)
        val back_shiny = findViewById<ImageView>(R.id.pokeMini2)
        val front_default = findViewById<ImageView>(R.id.pokeMini3)
        val front_shiny = findViewById<ImageView>(R.id.pokeMini4)

        val urlBackDefault = it.sprites.back_default
        val urlFrontDefault = it.sprites.front_default
        val urlBackShiny = it.sprites.back_shiny
        val urlFrontShiny = it.sprites.front_shiny


        pokename.text = it.name
        height.text = it.height.toString()
        weight.text = it.weight.toString()
        abilities.text = "${it.abilities[0].ability.name} and ${it.abilities[1].ability.name}"
        bexp.text = it.base_experience.toString()
        statsChild11.text = it.stats[0].base_stat.toString()
        statsChild12.text = it.stats[1].effort.toString()
        statsChild.text = it.moves[1].move.name
        statsChild2.text = it.moves[2].move.name
        statsChild3.text = it.moves[3].move.name//start
        statsChild4.text = it.moves[4].move.name
        statsChild5.text = it.moves[5].move.name
        statsChild6.text = it.moves[6].move.name
        statsChild7.text = it.moves[7].move.name
        statsChild8.text = it.moves[8].move.name
        statsChild9.text = it.moves[9].move.name
        statsChild10.text = it.moves[10].move.name

        Picasso.get().load(urlBackDefault).into(back_default)
        Picasso.get().load(urlFrontDefault).into(front_default)
        Picasso.get().load(urlBackShiny).into(back_shiny)
        Picasso.get().load(urlFrontShiny).into(front_shiny)

    }

    //This function initialize our DetailsActivity view or the view model
    private fun initViewModel(id: String): PokemonDetailsViewModel {

        val viewModel = ViewModelProvider(this).get(PokemonDetailsViewModel::class.java)
        viewModel.detailsLiveData.observe(this, Observer<Abilities> {
            if (it != null) {
                bindDetails(it)
            } else {
                Toast.makeText(this, "error in getting data", Toast.LENGTH_SHORT).show()
            }
        })

        //Here we observe the network connectivity and display our data when true or false
        connectivityLiveData.observe(this, Observer { isAvailable ->
            when (isAvailable) {
                true -> {
                    //3
                    viewModel.makeApiCall2(id)
                    cardView.visibility = View.VISIBLE
                    cardView2.visibility = View.VISIBLE
                    cardView3.visibility = View.VISIBLE
                    cardView4.visibility = View.VISIBLE
                    layOutCon.visibility = View.VISIBLE
                    statusButton2.visibility = View.GONE

                }
                false -> {
                    layOutCon.visibility = View.GONE
                    cardView.visibility = View.GONE
                    cardView2.visibility = View.GONE
                    cardView3.visibility = View.GONE
                    cardView4.visibility = View.GONE
                    statusButton2.visibility = View.VISIBLE
                }
            }
        })
        return viewModel

    }

}