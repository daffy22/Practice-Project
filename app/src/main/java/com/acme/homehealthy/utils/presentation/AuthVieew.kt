package com.acme.homehealthy.utils.presentation

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.acme.homehealthy.data.models.AuthVIewModel
import com.acme.homehealthy.data.models.Routine
import kotlinx.coroutines.delay
import com.acme.homehealthy.data.models.Training
import com.acme.homehealthy.screens.MainScreen
import com.acme.homehealthy.screens.Screen
import com.acme.homehealthy.utils.AuthResultContract
import com.google.android.gms.common.api.ApiException
import kotlinx.coroutines.Delay
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun AuthView(errorText: String?, onClick:()-> Unit){
    Scaffold {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            GoogleSignInButtonUi(text = "Sign Up With Google",
                loadingText = "Signing In....",
                onClicked = {onClick()})
            errorText?.let {
                Text(text = it)
            }
        }
    }
}


@InternalCoroutinesApi
@ExperimentalMaterialApi
@Composable
fun AuthScreen(authViewModel: AuthVIewModel, _routines: List<Routine>, _trainings: List<Training>, navController: NavController){

    val coroutineScope = rememberCoroutineScope()
    var text by remember { mutableStateOf<String?>(null)}
    val user by remember(authViewModel){authViewModel.user}.collectAsState()
    val signInRequestCode = 1

    val authResultLauncher =
        rememberLauncherForActivityResult(contract = AuthResultContract()){
                task ->
            try {
                val account = task?.getResult(ApiException::class.java)
                if (account==null){
                   // text = "mmmmmmmmmmm"
                    navController.navigate(Screen.SplashScreen.route)
                }else{
                    coroutineScope.launch {
                        authViewModel.signIn(email = account.email,displayName = account.displayName)
                    }
                }
            }catch (e:ApiException){
                text="Giiiiiiiiiiiiiiiiiiii"
            }
        }
    AuthView(errorText = text,onClick = {text=null
        authResultLauncher.launch(signInRequestCode)
    })
    user?.let{
        MainScreen(_routines,_trainings, navController)
    }
}