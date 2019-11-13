package com.example.userpost.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.userpost.model.User
import com.example.userpost.model.UserDao

@Database(entities = arrayOf(User::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}