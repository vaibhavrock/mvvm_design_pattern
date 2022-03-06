package com.example.mvvm_design_pattern.view.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_design_pattern.databinding.ListItemBinding
import com.example.mvvm_design_pattern.view.ui.DetailActivity


class VenueListAdapter(var context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var results: List<Any> = ArrayList()

    inner class UserViewHolder(val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val bindingView = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(bindingView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

           val item = results[position]

           val binding = (holder as UserViewHolder).binding
           binding.tvVenueName.text = ""
           binding.tvVenueAddress.text = ""

           binding.clRoot.setOnClickListener {
             gotoNextActivity()
           }
    }

    private fun gotoNextActivity() {
        val intent = Intent(context, DetailActivity::class.java)
        context.startActivity(intent)
    }

    override fun getItemCount(): Int {
        return results.size
    }

    fun setResults(items: List<Any>) {
        this.results = items
        notifyDataSetChanged()
    }


}