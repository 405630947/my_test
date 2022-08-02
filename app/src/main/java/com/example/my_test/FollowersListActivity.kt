package com.example.my_test

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.my_test.adapters.UserListRecyclerViewAdapter
import com.example.my_test.databinding.ActivityFollowersListBinding
import com.example.my_test.databinding.ActivityGithubUserDetailBinding
import com.example.my_test.viewmodels.GithubViewModel

class FollowersListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFollowersListBinding
    private val viewModel: GithubViewModel by viewModels()
    private val adapter = UserListRecyclerViewAdapter(listOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFollowersListBinding.inflate(layoutInflater)
        getFollowerData()
        setRecyclerView()
        dataObserve()
        setContentView(binding.root)
    }

    private fun dataObserve() {
        viewModel.userList.observe(this){
            adapter.updateList(it)
        }
    }

    private fun setRecyclerView() {
        binding.recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = adapter
    }

    private fun getFollowerData() {
        val userName = intent.getStringExtra("userName") ?: ""
        val mode = intent.getStringExtra("mode") ?: "0"
        viewModel.getFollowerList(userName, mode)
    }
}