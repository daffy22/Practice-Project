package com.acme.homehealthy.data.models

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AuthVIewModel: ViewModel() {
    private val _user: MutableStateFlow<User?> = MutableStateFlow(null)
    val user: StateFlow<User?> = _user

    fun signIn(email: String, displayName: String){
        _user.value = User(name = displayName, email = email, diasEntrenamiento = 5,id = 1,lastName = "Perez", objective = "up mm", experience = "Pro")
    }
}