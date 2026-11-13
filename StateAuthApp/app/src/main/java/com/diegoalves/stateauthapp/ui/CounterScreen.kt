package com.diegoalves.stateauthapp.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun CounterScreen(modifier: Modifier = Modifier){
    var counterWithRemember by remember { mutableIntStateOf(0) }

    var counterWithRememberSaveable by rememberSaveable {mutableIntStateOf(0) }

    CounterDisplay(
        counterWithRemember = counterWithRemember,
        counterWithRememberSaveable = counterWithRememberSaveable,
        onIncrementBoth = {
            counterWithRemember = counterWithRemember +1
            counterWithRememberSaveable = counterWithRememberSaveable +1
        },
        modifier = modifier
        )
}