package com.example.my_test.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.my_test.R
import com.example.my_test.data.Item
import com.example.my_test.databinding.ItemProfileDetailBinding

class ProfileDetailRecyclerViewAdapter(private var list: List<Item>) :
    RecyclerView.Adapter<ProfileDetailRecyclerViewAdapter.ProfileViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val binding =
            ItemProfileDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProfileViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        val currentItem = list[position]
        holder.onBind(currentItem)
    }

    override fun getItemCount() = list.size

    inner class ProfileViewHolder(private val binding: ItemProfileDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: Item) {
            Glide.with(binding.root.context).load(data.user.imageUrl).circleCrop()
                .placeholder(R.drawable.red_background)
                .into(binding.profileImage)
            binding.profileName.text = data.user.nickName
            binding.recyclerView.layoutManager = GridLayoutManager(binding.root.context, 5)
            binding.recyclerView.adapter = TagDetailRecyclerViewAdapter(data.tags)
        }

    }


    fun updateList(newList: List<Item>) {
        list = newList
        notifyDataSetChanged()
    }
}