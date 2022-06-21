package com.acme.homehealthy.screens.composableScreens

import android.content.Context
import android.provider.Settings.Global.getString
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.acme.homehealthy.R
import com.acme.homehealthy.screens.Screen
import com.acme.homehealthy.ui.theme.TransparentBlack
import com.acme.homehealthy.utils.LogInScreenModel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.GoogleAuthProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.acme.homehealthy.utils.LoadingState


@Composable
fun MainAutenticationBox(navController: NavController, viewModel: LogInScreenModel = viewModel()) {
    val context = LocalContext.current
    val state by viewModel.loadingState.collectAsState()
    // Equivalent of onActivityResult
    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
        try {
            val account = task.getResult(ApiException::class.java)!!
            val credential = GoogleAuthProvider.getCredential(account.idToken!!, null)
            viewModel.signWithCredential(credential)
        } catch (e: ApiException) {
            Log.w("TAG", "Google sign in failed", e)
        }
    }


    Box (
        modifier = Modifier
            .background(color = TransparentBlack)
            ){
        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(20.dp)
                .clickable { val options = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(R.string.webClient_id.toString())
                    .requestEmail()
                    .requestProfile()
                    .build()
                    val singInClient = GoogleSignIn.getClient(context, options)
                    launcher.launch(singInClient.signInIntent)
                }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.a),
                contentDescription = "Google button",
                tint = Color.Unspecified
            )
            Text(text = "Sign In with Google")
        }
        when(state.status) {
            LoadingState.Status.SUCCESS -> {
                Text(text = "Success")
            }
            LoadingState.Status.FAILED -> {
                Text(text = state.msg ?: "Error")
            }
            else -> {}
        }
    }
}

