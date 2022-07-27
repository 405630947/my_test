package com.example.my_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.my_test.adapters.ProfileDetailRecyclerViewAdapter
import com.example.my_test.adapters.TradeRecyclerViewAdapter
import com.example.my_test.data.TradeResponse
import com.example.my_test.databinding.ActivityMainBinding
import com.example.my_test.viewmodels.ProfileViewModel
import com.example.my_test.viewmodels.TradeViewModel
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: ProfileViewModel by viewModels()
    private val adapter = ProfileDetailRecyclerViewAdapter(listOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setRecyclerView()
        getApiData()
        dataObserve()
        setContentView(binding.root)
    }

    private fun dataObserve() {
        viewModel.profileList.observe(this) {
            adapter.updateList(it)
        }
    }

    private fun getApiData() {
        viewModel.getProfileList()
    }


    private fun setRecyclerView() {
        binding.recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
        binding.recyclerView.adapter = adapter
    }
}