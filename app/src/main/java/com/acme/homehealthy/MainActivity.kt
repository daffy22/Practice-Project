package com.acme.homehealthy

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.acme.homehealthy.data.remote.ApiClient
import com.acme.homehealthy.screens.Navigation
import com.acme.homehealthy.ui.theme.HomeHealthyTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.Manifest
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.Lifecycle
import com.acme.homehealthy.data.models.*
import kotlinx.coroutines.InternalCoroutinesApi

class MainActivity : ComponentActivity() {


    //variables
    var routines by mutableStateOf(listOf<Routine>())
    var trainings by mutableStateOf(listOf<Training>())
    var diets by mutableStateOf(listOf<Diet>())
    var users by mutableStateOf(listOf<User>())
    var user: User = User(5, "Sebastian", "Toulier", "sebas@gmail.com", "up mm", "Pro", 5)
    var routineDetailList by mutableStateOf(listOf<RoutineDetail>())

    @InternalCoroutinesApi
    @ExperimentalPermissionsApi
    @ExperimentalMaterialApi
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadRoutines()
        loadTrainings()
        //loadUsers()
        loadRoutineDetailList()
        loadDiets()
        setContent {
            HomeHealthyTheme {
                val permissionsState = rememberMultiplePermissionsState(
                    permissions = listOf(
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA,
                    )
                )
                val lifecycleOwner = LocalLifecycleOwner.current
                DisposableEffect(
                    key1 = lifecycleOwner,
                    effect = {
                        val observer = LifecycleEventObserver { _, event ->
                            if(event == Lifecycle.Event.ON_START) {
                                permissionsState.launchMultiplePermissionRequest()
                            }
                        }
                        lifecycleOwner.lifecycle.addObserver(observer)

                        onDispose {
                            lifecycleOwner.lifecycle.removeObserver(observer)
                        }
                    }
                )
                Navigation(routines, trainings, diets, user, routineDetailList)
            }
        }
    }

    private fun loadRoutines() {
        val routineInterface = ApiClient.buildRoutine()
        val fetchRoutines = routineInterface?.fetchRoutines()

        fetchRoutines?.enqueue(object : Callback<List<Routine>> {
            override fun onResponse(call: Call<List<Routine>>, response: Response<List<Routine>>) {
                routines = response.body()!!
            }

            override fun onFailure(call: Call<List<Routine>>, t: Throwable) {
                Log.d("MainActivity", t.toString())
            }
        })
    }

    private fun loadTrainings() {
        val trainingInterface = ApiClient.buildTraining()
        val fetchTraining = trainingInterface?.fetchTrainings()

        fetchTraining?.enqueue(object : Callback<List<Training>> {
            override fun onResponse(
                call: Call<List<Training>>,
                response: Response<List<Training>>
            ) {
                trainings = response.body()!!
            }

            override fun onFailure(call: Call<List<Training>>, t: Throwable) {
                Log.d("MainActivity", t.toString())
            }

        })
    }

    private fun loadDiets() {
        val dietInterface = ApiClient.buildDiets()
        val fetchDiets = dietInterface?.fetchDiet()

        fetchDiets?.enqueue(object : Callback<List<Diet>> {
            override fun onResponse(call: Call<List<Diet>>, response: Response<List<Diet>>) {
                diets = response.body()!!
            }

            override fun onFailure(call: Call<List<Diet>>, t: Throwable) {
                Log.d("MainActivity", t.toString())
            }
        })
    }

    private fun loadUsers() {
        val usersINterface = ApiClient.buildUsers()
        val fetchUsers = usersINterface?.fetchUsers()

        fetchUsers?.enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                users = response.body()!!
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Log.d("MainActivity", t.toString())
            }
        })
    }

    private fun loadRoutineDetailList(){
        val routineDetailInterface = ApiClient.buildRoutineDetail()
        val fetchRoutinesDetail = routineDetailInterface?.fetchRoutineDetails()
        fetchRoutinesDetail?.enqueue(object : Callback<List<RoutineDetail>>{
            override fun onResponse(
                call: Call<List<RoutineDetail>>,
                response: Response<List<RoutineDetail>>
            ) {
                routineDetailList = response.body()!!
            }

            override fun onFailure(call: Call<List<RoutineDetail>>, t: Throwable) {
                Log.d("FAIED: LOAD ROUTINE DETAILS", t.toString())
            }

        })
    }


}

