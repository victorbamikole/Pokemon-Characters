package com.example.reactiveprogramming.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.reactiveprogramming.R
import com.example.reactiveprogramming.adapter.RecyclerViewAdapter
import com.example.reactiveprogramming.connectivity.ConnectivityLiveData
import com.example.reactiveprogramming.model.RecyclerListData
import com.example.reactiveprogramming.model.Result
import com.example.reactiveprogramming.viewmodel.MainActivityViewModel
import kotlinx.android.synthetic.main.fragment_recycler_list.*

/**
 * A simple [Fragment] subclass.
 * Use the [RecyclerListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RecyclerListFragment : Fragment() {

    private lateinit var recyclerAdapter: RecyclerViewAdapter
    private lateinit var searchBtn: Button
    private var idd: Int = 200
    private lateinit var connectivityLiveData: ConnectivityLiveData


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        val view = inflater.inflate(R.layout.fragment_recycler_list, container, false)
        connectivityLiveData = ConnectivityLiveData(requireActivity().application)
        val firstName = view.findViewById<TextView>(R.id.pokeName)
        searchBtn = view.findViewById(R.id.searchBtn)
        val name = firstName?.text.toString()
        val bundle = Bundle()
        bundle.putString("keys", name)


        initViewModel(view)
        searchBtn.setOnClickListener {
            idd = searchBox.text.toString().toInt()
            initViewModel(idd)
        }


        return view
    }

    //This function initialize our recycler view or the view model
    private fun initViewModel(view: View?) {
        val recyclerView = view?.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView?.layoutManager = GridLayoutManager(activity, 2)
        val decoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        recyclerView?.addItemDecoration(decoration)
        recyclerAdapter = RecyclerViewAdapter()
        recyclerView?.adapter = recyclerAdapter

        //Click listener is set to the recyclerView
        recyclerAdapter.setOnItemClickListener(object : RecyclerViewAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                var idNew = position

                //The id of each item in RecyclerView list is passed to the DetailsActivity
                var intent = Intent(activity, DetailsActivity::class.java)
                intent.putExtra("key", idNew)
                startActivity(intent)
            }

        })

    }


    private fun initViewModel(idd: Int) {
        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getRecyclerListObserver().observe(viewLifecycleOwner, Observer<RecyclerListData> {
            if (it != null) {
                recyclerAdapter.setUpdatedData(it.results as ArrayList<Result>)
            } else {
                Toast.makeText(activity, "error in getting data", Toast.LENGTH_SHORT).show()
            }
        })


        //Here we observe the network connectivity and display our data when true or false

        connectivityLiveData.observe(viewLifecycleOwner, Observer { isAvailable ->
            //2
            when (isAvailable) {
                true -> {
                    //3
                    viewModel.makeApiCall(idd)
                    statusButton.visibility = View.GONE
                    recyclerView.visibility = View.VISIBLE
                    searchBox.visibility = View.VISIBLE
                }
                false -> {
                    statusButton.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                    searchBox.visibility = View.GONE
                    searchBtn.visibility = View.GONE
                }
            }
        })


    }

    companion object {

        @JvmStatic
        fun newInstance() =
            RecyclerListFragment()
    }


}

private fun ViewModelProvider.get(modelClass: Class<MainActivityViewModel>): MainActivityViewModel {

    return MainActivityViewModel()
}