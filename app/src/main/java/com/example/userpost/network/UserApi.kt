package com.example.userpost.network

import com.example.userpost.model.User
import io.reactivex.Observable
import retrofit2.http.GET


interface UserApi {
    /**
     * Get the list of the pots from the API
     */
    @GET("/users")
    fun getUsers(): Observable<List<User>>
}