package com.example.my_test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.my_test.adapters.ProfileDetailRecyclerViewAdapter
import com.example.my_test.adapters.TradeRecyclerViewAdapter
import com.example.my_test.adapters.UserListRecyclerViewAdapter
import com.example.my_test.data.TradeResponse
import com.example.my_test.databinding.ActivityMainBinding
import com.example.my_test.viewmodels.GithubViewModel
import com.example.my_test.viewmodels.ProfileViewModel
import com.example.my_test.viewmodels.TradeViewModel
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: GithubViewModel by viewModels()
    private val adapter = UserListRecyclerViewAdapter(listOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setRecyclerView()
        getUserData()
        dataObserve()
        setContentView(binding.root)
    }

    private fun dataObserve() {
        viewModel.userList.observe(this) {
            adapter.updateList(it)
        }
    }

    private fun getUserData() {
        viewModel.getUserList()
    }


    private fun setRecyclerView() {
        binding.recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = adapter.apply {
            itemClick = {
                goToDetail(it)
            }
        }
    }

    private fun goToDetail(data: String) {
        val intent = Intent(this, UserDetailActivity::class.java).apply {
            putExtra("login",data)
        }
        startActivity(intent)
    }
}