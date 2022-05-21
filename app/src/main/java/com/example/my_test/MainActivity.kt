package com.example.my_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.my_test.adapters.TradeRecyclerViewAdapter
import com.example.my_test.data.TradeResponse
import com.example.my_test.databinding.ActivityMainBinding
import com.example.my_test.viewmodels.TradeViewModel
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel:TradeViewModel by viewModels()
    private val adapter = TradeRecyclerViewAdapter(listOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setRecyclerView()
        getApiData()
        dataObserve()
        setContentView(binding.root)
    }

    private fun dataObserve() {
        viewModel.tradeData.observe(this,{
            adapter.updateList(it.reversed().subList(0,40))
            viewModel.connectWebSocket()
        })
        viewModel.socketTradeData.observe(this,{
            val list = adapter.getDataList().reversed().toMutableList()
            list.add(it)
            adapter.updateList(list.reversed().subList(0,40))
        })
    }

    private fun setRecyclerView() {
        binding.recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = adapter
    }

    private fun getApiData() {
        viewModel.getTradeData()
    }
}