package com.example.userpost.base

import androidx.lifecycle.ViewModel
import com.example.userpost.injection.component.ViewModelInjector
import com.example.userpost.injection.component. DaggerViewModelInjector
import com.example.userpost.injection.module.NetworkModule
import com.example.userpost.model.User
import com.example.userpost.ui.user.UserListViewModel

abstract class BaseViewModel: ViewModel(){
    private val injector: ViewModelInjector = DaggerViewModelInjector
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is UserListViewModel -> injector.inject(this)
        }
    }
}