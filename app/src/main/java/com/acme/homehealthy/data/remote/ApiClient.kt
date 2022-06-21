package com.acme.homehealthy.data.remote

import com.acme.homehealthy.data.remote.interfaces.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val API_BASE_URL = "https://60c255b2069afc0017f4a2ca.mockapi.io/"
    private const val API_BASE_URL_DIET = "https://60c2e09f917002001739da47.mockapi.io/"
    private const val API_BASE_URL_USER = "https://60c2e09f917002001739da47.mockapi.io/"


    //Routines
    var routineInterface: RoutineInterface? = null

    fun buildRoutine(): RoutineInterface? {
        val builder: Retrofit.Builder = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
        var retrofit: Retrofit = builder.build()
        routineInterface = retrofit.create(RoutineInterface::class.java)
        return routineInterface as RoutineInterface
    }

    //Trainings
    var trainingInterface: TrainingInterface? = null

    fun buildTraining(): TrainingInterface? {
        val builder: Retrofit.Builder = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
        var retrofit: Retrofit = builder.build()
        trainingInterface = retrofit.create(TrainingInterface::class.java)
        return trainingInterface as TrainingInterface
    }

    //Diet
    var dietInterface: DietInterface?=null
    fun buildDiets(): DietInterface?{
        val builder: Retrofit.Builder = Retrofit.Builder()
            .baseUrl(API_BASE_URL_DIET)
            .addConverterFactory(GsonConverterFactory.create())
        var retrofit: Retrofit = builder.build()
        dietInterface = retrofit.create(DietInterface::class.java)
        return dietInterface as DietInterface
    }

    //Diet
    var userInterface: UserInterface?=null
    fun buildUsers(): UserInterface?{
        val builder: Retrofit.Builder = Retrofit.Builder()
            .baseUrl(API_BASE_URL_USER)
            .addConverterFactory(GsonConverterFactory.create())
        var retrofit: Retrofit = builder.build()
        userInterface = retrofit.create(UserInterface::class.java)
        return userInterface as UserInterface
    }

    //RoutineDetail
    var routineDetailInterface:RoutineDetailInterface? = null
    fun buildRoutineDetail(): RoutineDetailInterface?{
        val builder: Retrofit.Builder = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
        var retrofit:Retrofit = builder.build()
        routineDetailInterface = retrofit.create(RoutineDetailInterface::class.java)
        return routineDetailInterface as RoutineDetailInterface
    }



}