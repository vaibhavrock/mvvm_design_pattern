package com.example.mvvm_design_pattern.view.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvm_design_pattern.databinding.AdapterUserItemBinding
import com.example.mvvm_design_pattern.model.Item
import com.example.mvvm_design_pattern.view.ui.DetailActivity2


class UsersAdapter(var context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var results: List<Item> = ArrayList()
    private var customViewType = 0

    inner class UserViewHolder(val binding: AdapterUserItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val bindingView = AdapterUserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(bindingView)
    }

    fun setViewType(type:Int){
        this.customViewType = type
    }

    override fun getItemViewType(position: Int): Int {
        return customViewType
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        with(results[position]) {
            val binding = (holder as UserViewHolder).binding
            binding.userNameTv.text = this.display_name
            binding.userReputationTv.text = this.reputation.toString()
            Glide.with(holder.itemView)
                .load(this.profile_image)
                .fitCenter()
                .circleCrop()
                .into(binding.userImage)

            binding.rootLayout.setOnClickListener {
                gotoNextActivity()
            }

        }
    }

    private fun Item.gotoNextActivity() {
        val intent = Intent(context, DetailActivity2::class.java)
        intent.putExtra("display_name", this.display_name)
        intent.putExtra("profile_image", this.profile_image)
        intent.putExtra("reputation", this.reputation.toString())
        intent.putExtra("location", this.location)
        context.startActivity(intent)
    }

    override fun getItemCount(): Int {
        return results.size
    }

    fun setResults(items: List<Item>) {
        this.results = items
        notifyDataSetChanged()
    }


}