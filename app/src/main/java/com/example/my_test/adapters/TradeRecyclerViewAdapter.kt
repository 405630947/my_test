package com.example.my_test.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.my_test.data.TradeResponse
import com.example.my_test.databinding.ItemTradeDetailBinding
import com.example.my_test.utils.keepTwoDigitAfterDot
import com.example.my_test.utils.toTimeString

class TradeRecyclerViewAdapter(private var list: List<TradeResponse>) :
    RecyclerView.Adapter<TradeRecyclerViewAdapter.TradeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TradeViewHolder {
        val binding =
            ItemTradeDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TradeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TradeViewHolder, position: Int) {
        val currentItem = list[position]
        holder.onBind(currentItem)
    }

    override fun getItemCount() = list.size

    inner class TradeViewHolder(private val binding: ItemTradeDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: TradeResponse) {
            binding.tradeTime.text = data.t.toTimeString()
            binding.tradePrice.text = data.p.keepTwoDigitAfterDot()
            binding.tradeCount.text = data.q
        }

    }

    fun updateList(newList: List<TradeResponse>) {
        list = newList
        notifyDataSetChanged()
    }

    fun getDataList() = list
}