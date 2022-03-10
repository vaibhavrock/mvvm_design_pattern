package com.example.mvvm_design_pattern.view.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm_design_pattern.databinding.ActivityMainBinding
import com.example.mvvm_design_pattern.network.Resource
import com.example.mvvm_design_pattern.repository.UserRepository
import com.example.mvvm_design_pattern.view.adapter.UsersAdapter
import com.example.mvvm_design_pattern.viewmodel.MyViewModelFactory
import com.example.mvvm_design_pattern.viewmodel.UserViewModel


class MainActivity : AppCompatActivity() {

    //view binding
    private lateinit var binding: ActivityMainBinding

    // viewModel
    private lateinit var viewModel: UserViewModel

    //Adapter
    private var userAdapter: UsersAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        userAdapter= UsersAdapter(this)
        viewModel = ViewModelProvider(this,MyViewModelFactory(this.application, UserRepository()))[UserViewModel::class.java]

        //link recyclerview
        binding.rvVenue.layoutManager = LinearLayoutManager(this)
        binding.rvVenue.adapter = userAdapter

        // viewModel observer
        viewModel.usersListLiveData.observe(this) {
            when (it) {
                is Resource.Success -> {
                    binding.pbMain.visibility = View.GONE
                    userAdapter!!.setResults(it.data.items)
                }
                is Resource.Error -> {
                    binding.pbMain.visibility = View.GONE
                    Toast.makeText(this,it.message, Toast.LENGTH_SHORT).show()
                }
                is Resource.Loading -> {
                    binding.pbMain.visibility = View.VISIBLE
                }
            }
        }
        // api calling
        viewModel.getUserList(20)

    }
}