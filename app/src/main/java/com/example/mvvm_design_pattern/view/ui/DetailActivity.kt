package com.example.mvvm_design_pattern.view.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mvvm_design_pattern.databinding.ActivityDetailsBinding

class DetailActivity : AppCompatActivity() {

    //view binding
    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_details)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}