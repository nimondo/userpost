package com.example.userpost.injection

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.userpost.model.database.AppDatabase
import com.example.userpost.ui.user.UserListViewModel

class ViewModelFactory(private val activity: AppCompatActivity): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserListViewModel::class.java)) {
            val db = Room.databaseBuilder(activity.applicationContext, AppDatabase::class.java, "users").build()
            @Suppress("UNCHECKED_CAST")
            return UserListViewModel(db.userDao()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}