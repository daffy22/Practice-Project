package com.acme.homehealthy.data.remote.interfaces

import com.acme.homehealthy.data.models.Training
import com.acme.homehealthy.data.models.User
import retrofit2.Call
import retrofit2.http.GET

interface UserInterface {

    @GET("user")
    fun fetchUsers(): Call<List<User>>
}