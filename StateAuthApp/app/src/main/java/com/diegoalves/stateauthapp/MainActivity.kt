package com.diegoalves.stateauthapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.ui.Modifier
import com.diegoalves.stateauthapp.ui.CounterScreen
import com.diegoalves.stateauthapp.ui.theme.StateAuthAppTheme

/**
 * Activity principal. Só monta o tema e exibe o CounterScreen.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StateAuthAppTheme {
                CounterScreen(modifier = Modifier.safeDrawingPadding())
            }
        }
    }
}