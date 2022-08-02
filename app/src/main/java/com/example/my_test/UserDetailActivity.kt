package com.example.my_test

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.my_test.databinding.ActivityGithubUserDetailBinding
import com.example.my_test.viewmodels.GithubViewModel

class UserDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGithubUserDetailBinding
    private val viewModel: GithubViewModel by viewModels()
    private var userName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGithubUserDetailBinding.inflate(layoutInflater)
        getApiData()
        dataObserve()
        setContentView(binding.root)
    }

    private fun dataObserve() {
        viewModel.userDetail.observe(this) {
            binding.apply {
                Glide.with(this@UserDetailActivity).load(it.avatarUrl).circleCrop().into(userAvatar)
                userName.text = "Name : ${it.name}"
                userBio.text = "Bio : ${it.bio}"
                userLocation.text = "Location : ${it.location}"
                userLogin.text = "Login : ${it.login}"
                userBlog.text = "Blog : ${it.blog}"
                it.siteAdmin?.let {
                    if (it) {
                        userIsAdmin.visibility = View.VISIBLE
                    } else {
                        userIsAdmin.visibility = View.GONE
                    }
                }
                userFollowers.setOnClickListener {
                    val intent =
                        Intent(this@UserDetailActivity, FollowersListActivity::class.java).apply {
                            putExtra("userName", this@UserDetailActivity.userName)
                            putExtra("mode","0")
                        }
                    startActivity(intent)
                }
                userFollowing.setOnClickListener {
                    val intent =
                        Intent(this@UserDetailActivity, FollowersListActivity::class.java).apply {
                            putExtra("userName", this@UserDetailActivity.userName)
                            putExtra("mode","1")
                        }
                    startActivity(intent)
                }
            }
        }
    }

    private fun getApiData() {
        userName = intent.getStringExtra("login") ?: ""
        Log.d("userName", userName)
        viewModel.getUserDetail(userName)
    }
}