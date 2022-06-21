package com.acme.homehealthy.data.remote.interfaces

import com.acme.homehealthy.data.models.Training
import retrofit2.Call
import retrofit2.http.GET

interface TrainingInterface {

    @GET("trainings")
    fun fetchTrainings(): Call<List<Training>>
}