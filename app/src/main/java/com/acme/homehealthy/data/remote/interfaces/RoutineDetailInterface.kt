package com.acme.homehealthy.data.remote.interfaces

import android.telecom.Call
import com.acme.homehealthy.data.models.RoutineDetail
import retrofit2.http.GET

interface RoutineDetailInterface {
    @GET("/RoutineDetail")
    fun fetchRoutineDetails():retrofit2.Call<List<RoutineDetail>>
}
