package com.acme.homehealthy.data.remote.interfaces

import com.acme.homehealthy.data.models.TrainingRoutine
import retrofit2.Call
import retrofit2.http.GET

interface TrainingRoutineInterface {

    @GET("/routines/{routineId}")
    fun fetchTrainingsByRoutineId(): Call<List<TrainingRoutine>>
}