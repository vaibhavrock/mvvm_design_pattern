package com.example.mvvm_design_pattern.view.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.mvvm_design_pattern.databinding.ActivityDetailsBinding
import com.example.mvvm_design_pattern.viewmodel.VenueViewModel

class DetailActivity : AppCompatActivity() {

    //view binding
    private lateinit var binding: ActivityDetailsBinding

    // viewModel
    private lateinit var viewModel: VenueViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_details)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModel = ViewModelProvider(this).get(VenueViewModel::class.java)
        intent.getStringExtra("id")?.let { viewModel.getVenueDetails(it) }

        // viewModel observer
        viewModel.venueDetailsLiveData?.observe(this) { response ->
            print("response: $response")
            // need to parse
            if (response != null) {

                // Rating bar:
                // binding.rBDetails

                //

                binding.tvAddressDetails

                Glide.with(this)
                    .load(response)
                    .fitCenter()
                    .circleCrop()
                    .into(binding.imgVenuePhotoDetails)

            }
        }
        //

    }
}