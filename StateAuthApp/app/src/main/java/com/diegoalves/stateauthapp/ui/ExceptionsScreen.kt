

package com.diegoalves.stateauthapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun ExceptionsScreen(
    onBackToMenu: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        TextButton(onClick = onBackToMenu) {
            Text(text = "Back to menu")
        }
        Text(text = "Exceptions", style = MaterialTheme.typography.headlineMedium)
        Text(
            text = "The demonstrations will appear here.",
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}