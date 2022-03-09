package com.example.mvvm_design_pattern.view.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm_design_pattern.databinding.ActivityMainBinding
import com.example.mvvm_design_pattern.view.adapter.UsersAdapter
import com.example.mvvm_design_pattern.viewmodel.VenueViewModel

class MainActivity : AppCompatActivity() {

    //view binding
    private lateinit var binding: ActivityMainBinding

    // viewModel
    private lateinit var viewModel: VenueViewModel

    //Adapter
    //private var adapter: VenueListAdapter? = null
    private var userAdapter: UsersAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        userAdapter= UsersAdapter(this)
        viewModel = ViewModelProvider(this).get(VenueViewModel::class.java)

        //link recyclerview
        binding.rvVenue.layoutManager = LinearLayoutManager(this)
        binding.rvVenue.adapter = userAdapter

        // visible progressbar
        binding.pbMain.visibility = View.VISIBLE
        // user list api calling
        viewModel.getUserList()

        // viewModel observer
        viewModel.usersListLiveData?.observe(this) { response ->
            //print("response: $response")
            // hide progress bar
            binding.pbMain.visibility = View.GONE

            if (response != null) {
                userAdapter!!.setResults(response.items)
            }

        }

    }
}