package com.example.userpost.injection.component

import com.example.userpost.injection.module.NetworkModule
import com.example.userpost.ui.user.UserListViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {
    /**
     * Injects required dependencies into the specified PostListViewModel.
     * @param userListViewModel UserListViewModel in which to inject the dependencies
     */
    fun inject(userListViewModel: UserListViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun networkModule(networkModule: NetworkModule): Builder
    }
}