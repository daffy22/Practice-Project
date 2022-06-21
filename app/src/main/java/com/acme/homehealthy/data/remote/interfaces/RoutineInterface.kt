package com.acme.homehealthy.data.remote.interfaces

import com.acme.homehealthy.data.models.Routine
import retrofit2.Call
import retrofit2.http.GET


interface RoutineInterface {
    //TODO: change routines to 'rutines' when workings with the real API
    @GET("routines")
    fun fetchRoutines(): Call<List<Routine>>
}