package com.example.my_test.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.my_test.databinding.ItemTagDetailBinding

class TagDetailRecyclerViewAdapter(private val list: List<String>) :
    RecyclerView.Adapter<TagDetailRecyclerViewAdapter.TagViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder {
        val binding =
            ItemTagDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TagViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TagViewHolder, position: Int) {
        val currentItem = list[position]
        holder.onBind(currentItem)
    }

    override fun getItemCount() = list.size


    inner class TagViewHolder(private val binding: ItemTagDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: String) {
            binding.tag.text = data
        }

    }
}