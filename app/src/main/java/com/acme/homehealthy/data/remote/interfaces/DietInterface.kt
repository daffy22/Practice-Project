package com.acme.homehealthy.data.remote.interfaces

import com.acme.homehealthy.data.models.Diet
import retrofit2.Call
import retrofit2.http.GET

interface DietInterface {
    @GET("Diet")
    fun fetchDiet(): Call<List<Diet>>
}