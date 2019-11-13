package com.example.userpost.ui.user

import androidx.lifecycle.MutableLiveData
import com.example.userpost.base.BaseViewModel
import com.example.userpost.model.User

class UserViewModel: BaseViewModel() {
    private val userName = MutableLiveData<String>()
    private val userEmail = MutableLiveData<String>()

    fun bind(user:User){
        userName.value = user.name
        userEmail.value = user.email
    }

    fun getUserName():MutableLiveData<String>{
        return userName
    }

    fun getUserMail():MutableLiveData<String>{
        return userEmail
    }
}