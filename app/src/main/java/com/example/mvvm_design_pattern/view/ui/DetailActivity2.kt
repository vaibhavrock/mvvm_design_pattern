package com.example.mvvm_design_pattern.view.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.mvvm_design_pattern.databinding.ActivityDetails2Binding

class DetailActivity2 : AppCompatActivity() {

    //view binding
    private lateinit var binding: ActivityDetails2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_details)

        binding = ActivityDetails2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setData()

    }


    private fun setData(){

        intent.getStringExtra("display_name")?.let {
            binding.profileNameprofileName.text = it
        }
        intent.getStringExtra("profile_image")?.let {
            Glide.with(this)
            .load(it)
            .fitCenter()
            .circleCrop()
            .into(binding.profileImg)
        }
        intent.getStringExtra("reputation")?.let {
            binding.profileReputationTv.text = it
        }
        intent.getStringExtra("location")?.let {
            binding.profileLocationTv.text = it
        }

    }
}