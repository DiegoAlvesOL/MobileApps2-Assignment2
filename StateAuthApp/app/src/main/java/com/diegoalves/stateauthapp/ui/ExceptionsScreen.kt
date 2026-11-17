

package com.diegoalves.stateauthapp.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.diegoalves.stateauthapp.exceptions.DemoResult


@Composable
fun ExceptionsScreen(
    preconditionResults: List<DemoResult>,
    onRunPreconditionDemo: () -> Unit,
    onBackToMenu: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ){
        TextButton(onClick = onBackToMenu) {
            Text(text = "Back to menu")
        }
        Text(text = "Exceptions", style = MaterialTheme.typography.headlineMedium)
        Text(
            text = "Exceptions 1: check() and require()",
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Text(
            text = "Results also appear in Logcat with tag:ExceptionsLab",
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Button(
            onClick = onRunPreconditionDemo,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Run exceptions 1 demo")
        }

        if (preconditionResults.isNotEmpty()){
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    preconditionResults.forEach { result ->
                        Text(
                            text = result.message,
                            color = if (result.isError) {
                                MaterialTheme.colorScheme.error
                            } else {
                                MaterialTheme.colorScheme.tertiary
                            }
                        )
                    }
                }
            }

        }
    }
}
