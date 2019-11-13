package com.example.userpost.ui.user

import android.app.PendingIntent.getActivity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.userpost.R
import com.example.userpost.databinding.ItemUserBinding
import com.example.userpost.model.User


class UserListAdapter: RecyclerView.Adapter<UserListAdapter.ViewHolder>() {
    private lateinit var userList:List<User>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListAdapter.ViewHolder {
        val binding: ItemUserBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_user, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserListAdapter.ViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    override fun getItemCount(): Int {
        return if(::userList.isInitialized) userList.size else 0
    }

    fun updateUserList(postList:List<User>){
        this.userList = postList
        notifyDataSetChanged()
    }

//    class UserListListener(val clickListener: (userId: Int) -> Unit) {
//        fun onClick(user : User) = clickListener(user.id)
//    }

    class ViewHolder(private val binding: ItemUserBinding):RecyclerView.ViewHolder(binding.root){
        private val viewModel = UserViewModel()
        fun bind(user:User){
            viewModel.bind(user)
            binding.viewModel = viewModel
        }
    }
}