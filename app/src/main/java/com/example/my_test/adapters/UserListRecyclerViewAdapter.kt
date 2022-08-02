package com.example.my_test.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.my_test.data.User
import com.example.my_test.data.UserListResponse
import com.example.my_test.databinding.ItemGithubUserListBinding

class UserListRecyclerViewAdapter(private var list: List<UserListResponse>) :
    RecyclerView.Adapter<UserListRecyclerViewAdapter.UserListViewHolder>() {

    var itemClick: ((String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        val binding =
            ItemGithubUserListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        val currentItem = list[position]
        holder.onBind(currentItem)
    }

    override fun getItemCount() = list.size

    inner class UserListViewHolder(private val binding: ItemGithubUserListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(data: UserListResponse) {
            Glide.with(binding.root.context).load(data.avatarUrl).circleCrop()
                .into(binding.userAvater)
            data.siteAdmin?.let {
                if (it) {
                    binding.userIsAdmin.visibility = View.VISIBLE
                } else {
                    binding.userIsAdmin.visibility = View.GONE

                }
            }
            data.login.let {
                binding.userLogin.text = "Login : $it"
            }
            binding.root.setOnClickListener {
                data.login?.let {
                    itemClick?.invoke(it)
                }
            }
        }

    }

    fun updateList(newList: List<UserListResponse>) {
        list = newList
        notifyDataSetChanged()
    }
}