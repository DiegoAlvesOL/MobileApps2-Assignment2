package com.diegoalves.stateauthapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.diegoalves.stateauthapp.ui.CounterScreen
import com.diegoalves.stateauthapp.ui.LoginScreen
import com.diegoalves.stateauthapp.ui.theme.StateAuthAppTheme
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

/**
 * Activity principal. Só monta o tema e exibe o CounterScreen.
 */
class MainActivity : ComponentActivity() {

    private val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    private var signedInEmail by mutableStateOf<String?>(null)

    private var authErrorMessage by mutableStateOf<String?>(null)

    private var isAuthLoading by mutableStateOf(false)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        signedInEmail = firebaseAuth.currentUser?.email


        setContent {
            StateAuthAppTheme {

                val currentEmail = signedInEmail

                if (currentEmail == null){
                    LoginScreen(
                        errorMessage = authErrorMessage,
                        isLoading = isAuthLoading,
                        onSignIn = { email, password -> signIn(email, password) },
                        onRegister = { email, password -> register(email, password)},
                        modifier = Modifier.safeDrawingPadding()
                    )
                }else{
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .safeDrawingPadding()
                            .padding(16.dp)
                    ) {
                        Text(text = " Signed in as $currentEmail")
                        Button(onClick = {signOut()}) {
                            Text(text = "Sign out")
                        }
                        CounterScreen()
                    }
                }
            }
        }
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

