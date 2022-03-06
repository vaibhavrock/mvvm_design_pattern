package com.example.mvvm_design_pattern.view.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm_design_pattern.databinding.ActivityMainBinding
import com.example.mvvm_design_pattern.view.adapter.VenueListAdapter
import com.example.mvvm_design_pattern.viewmodel.VenueViewModel

class MainActivity : AppCompatActivity() {

    //view binding
    private lateinit var binding: ActivityMainBinding

    // viewModel
    private lateinit var viewModel: VenueViewModel

    //Adapter
    private var adapter: VenueListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        adapter = VenueListAdapter(this)
        viewModel = ViewModelProvider(this).get(VenueViewModel::class.java)

        //link recyclerview
        binding.rvVenue.layoutManager = LinearLayoutManager(this)
        binding.rvVenue.adapter = adapter


        binding.etSearch.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                val userInput = s.toString()
                if(userInput.length >4)
                 viewModel.getVenueList(userInput)
            }

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
            }
        })



        // viewModel observer
        viewModel.venueListLiveData?.observe(this) { response ->
            print("response: $response")
            if (response != null) {
                // need to check
                adapter!!.setResults(response as List<Any>)
            }
        }
        //
    }
}