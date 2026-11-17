package com.diegoalves.stateauthapp

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.diegoalves.stateauthapp.ui.AppScreen
import com.diegoalves.stateauthapp.ui.CounterScreen
import com.diegoalves.stateauthapp.ui.ExceptionsScreen
import com.diegoalves.stateauthapp.ui.LoginScreen
import com.diegoalves.stateauthapp.ui.MainMenuScreen
import com.diegoalves.stateauthapp.ui.theme.StateAuthAppTheme
import com.google.android.gms.tasks.Task

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.diegoalves.stateauthapp.exceptions.DemoResult
import com.diegoalves.stateauthapp.exceptions.runCustomExceptionDemo
import com.diegoalves.stateauthapp.exceptions.runPreconditionDemo



class MainActivity : ComponentActivity() {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    private var currentScreen by mutableStateOf(AppScreen.MENU)

    private var signedInEmail by mutableStateOf<String?>(null)

    private var authErrorMessage by mutableStateOf<String?>(null)

    private var isAuthLoading by mutableStateOf(false)

    private var preconditionResults by mutableStateOf<List<DemoResult>>(emptyList())

    private var customExceptionResults by mutableStateOf<List<DemoResult>>(emptyList())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT)
        )

        signedInEmail = firebaseAuth.currentUser?.email

        setContent {
            StateAuthAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BackHandler(enabled = currentScreen != AppScreen.MENU){
                        backToMenu()
                    }

                    when(currentScreen){
                        AppScreen.MENU ->{
                            MainMenuScreen(
                                onOpenLogin = {currentScreen = AppScreen.LOGIN},
                                onOpenExceptions = {currentScreen = AppScreen.EXCEPTIONS},
                                modifier = Modifier.safeDrawingPadding()
                            )
                        }

                        AppScreen.LOGIN ->{
                            val currentEmail = signedInEmail

                            if (currentEmail ==null){
                                LoginScreen(
                                    errorMessage = authErrorMessage,
                                    isLoading = isAuthLoading,
                                    onSignIn = { email, password -> signIn(email, password) },
                                    onRegister = { email, password -> register(email, password) },
                                    onBackToMenu = {backToMenu()},
                                    modifier = Modifier.safeDrawingPadding()
                                )
                            }else{
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .safeDrawingPadding()
                                        .padding(16.dp)
                                ) {
                                    Text(
                                        text = "Signed in as $currentEmail",
                                        style = MaterialTheme.typography.titleMedium
                                    )
                                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                        OutlinedButton(onClick = { signOut()}) {
                                            Text(text = "Sign out")
                                        }
                                        OutlinedButton(onClick = {backToMenu()}) {
                                            Text(text = "Back to menu")
                                        }
                                    }

                                    CounterScreen()
                                }
                            }
                        }
                        AppScreen.EXCEPTIONS -> {
                            ExceptionsScreen(
                                preconditionResults = preconditionResults,
                                onRunPreconditionDemo = {
                                    preconditionResults = runPreconditionDemo()
                                },
                                customExceptionResults = customExceptionResults,
                                onRunCustomExceptionDemo = {
                                    customExceptionResults = runCustomExceptionDemo()
                                },
                                onBackToMenu = { backToMenu() },
                                modifier = Modifier.safeDrawingPadding()
                            )
                        }
                    }
                }
            }
        }


//        setContent {
//            StateAuthAppTheme {
//
//                val currentEmail = signedInEmail
//
//                if (currentEmail == null){
//                    LoginScreen(
//                        errorMessage = authErrorMessage,
//                        isLoading = isAuthLoading,
//                        onSignIn = { email, password -> signIn(email, password) },
//                        onRegister = { email, password -> register(email, password)},
//                        modifier = Modifier.safeDrawingPadding()
//                    )
//                }else{
//                    Column(
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .safeDrawingPadding()
//                            .padding(16.dp)
//                    ) {
//                        Text(text = " Signed in as $currentEmail")
//                        Button(onClick = {signOut()}) {
//                            Text(text = "Sign out")
//                        }
//                        CounterScreen()
//                    }
//                }
//            }
//        }
    }

    private fun backToMenu(){
        currentScreen = AppScreen.MENU
    }
    private fun signIn(email: String, password: String) {
        if (email.isBlank() || password.isBlank()) {
            authErrorMessage = "Enter your email and password."
            return
        }

        isAuthLoading = true
        authErrorMessage = null

        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task -> finishAuthentication(task) }
    }

    private fun register(email: String, password: String) {
        if (email.isBlank() || password.isBlank()) {
            authErrorMessage = "Enter your email and  password "
            return
        }

        isAuthLoading = true
        authErrorMessage = null

        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task -> finishAuthentication(task) }
    }

    private fun signOut(){
        firebaseAuth.signOut()
        signedInEmail = null
    }


    private fun finishAuthentication(task: Task<AuthResult>){
        isAuthLoading = false

        if (task.isSuccessful){
            signedInEmail = firebaseAuth.currentUser?.email
        }else{
            authErrorMessage = task.exception?.localizedMessage ?: "Authentication failed"
        }
    }
}

