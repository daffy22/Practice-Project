package com.acme.homehealthy.screens

import android.annotation.SuppressLint

sealed class Screen(val route: String){
    object MainScreen: Screen("main_screen")
    object DietScreen: Screen("diet_screen")
    object ProfileScreen: Screen("profile_screen")
    object AuthScreen: Screen("auth_screen")
    object DietDetailScreen: Screen("diet_detail_screen")
    object RoutineDetailScreen: Screen("routine_detail_screen")
    @SuppressLint("CustomSplashScreen")
    object SplashScreen: Screen("splash_screen")



    fun withArgs(vararg args: String): String{
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
