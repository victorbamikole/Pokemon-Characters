package com.example.reactiveprogramming.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.reactiveprogramming.R
import com.example.reactiveprogramming.model.Abilities
import com.example.reactiveprogramming.model.RecyclerListData
import com.example.reactiveprogramming.model.Result
import com.example.reactiveprogramming.viewmodel.Method.getIndexFromUrl
//import com.example.multithreadingandnetworkingtask7.viewmodel.Methods.getIndexFromUrl
import com.squareup.picasso.Picasso


//This class creates the adapter for the recycler view
class RecyclerViewAdapter: RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>(){

    //Initialize the click listener interface
    private lateinit var mlistener: onItemClickListener

    //create an interface for item clicklistener
    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){

        mlistener = listener
    }

    //This variable holds the data to be displayed on the recycler view
    var items = ArrayList<Result>()

    fun setUpdatedData(items: ArrayList<Result>){
        this.items.clear()
        this.items = items
        notifyDataSetChanged()
    }

    //This class holds
    class MyViewHolder(view: View, listener: onItemClickListener): RecyclerView.ViewHolder(view) {

        val imageThumb = view.findViewById<ImageView>(R.id.imageThumb)
        val pokerName = view.findViewById<TextView>(R.id.pokeName)
//        val abilities = view.findViewById<TextView>(R.id.ability)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }

        //This function binds the recycler view to the recycler data
        fun bind(data: Result) {
            pokerName.text = data.name.capitalize()
            val baseurl = data.url


            //This variable calls the getIndexFromUrlFunction and loads the image data from api to the view with the picasso library
            val getImageNumber = getIndexFromUrl(baseurl)
            Picasso.get()
                .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${getImageNumber}.png")
                .into(imageThumb)

        }

//        fun bind2(data2: Abilities) {
//            abilities.text = data2.base_experience.toString()
//
//
//        }
    }

    //In this function, We inflate the recycler view layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_row,parent,false)
        return MyViewHolder(view, mlistener)
    }

    //This function
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items.get(position))
    }

    //This function returns the number of each items in the recyclerView
    override fun getItemCount(): Int {
        return items.size
    }
}