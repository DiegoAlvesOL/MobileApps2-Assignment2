package com.diegoalves.stateauthapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CounterDisplay(
    counterWithRemember: Int,
    counterWithRememberSaveable: Int,
    onIncrementBoth: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ){
        Text(text = "Counter with remember: $counterWithRemember")
        Text(text = "Counter with rememberSaveable: $counterWithRememberSaveable")

        Button(onClick = onIncrementBoth) {
            Text(text = "Increment both")
        }
    }
}