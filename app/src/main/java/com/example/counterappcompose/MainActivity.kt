package com.example.counterappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel // Важно для Compose!
import androidx.compose.ui.res.stringResource

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // The component that will contain all the View logic
            CounterScreen()
        }
    }
}

/**
 * The main Composable screen (View).
 * It accepts a ViewModel and uses it to build the UI.
 */
@Composable
fun CounterScreen(viewModel: CounterViewModel = viewModel()) {
    // The key point of MVI in Compose is observing StateFlow.
    // collectAsState() subscribes to the Flow and automatically triggers recomposition
    // of the entire Composable if the State value changes.
    val state by viewModel.state.collectAsState()

    CounterComposable(
        state = state,
        onIncrementIntent = {
            viewModel.handleIntent(CounterIntent.Increment)
        },
        onDecrementIntent = {
            viewModel.handleIntent(CounterIntent.Decrement)
        }
    )
}


/**
 * The UI elements themselves (Layout). There is no logic here, only data representation.
 */
@Composable
fun CounterComposable(
    state: CounterState,
    onIncrementIntent: () -> Unit, // This is our callback for Intents
    onDecrementIntent: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // 1. Displaying the current State (Displaying the Model)

        Text(
            text = "Counter: ${state.count}",
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween, // Равное расстояние между кнопками
        ) {
            // 2. The "Reduce" button (Sending the Intent)
            Button(
                onClick = onDecrementIntent,
                enabled = state.count > 0 // Элемент UI может быть отключен на основе State
            ) {
                Text(stringResource(R.string.reduce))
            }

            // 3. The "Increase" button (Sending the Intent)
            Button(
                onClick = onIncrementIntent
            ) {
                Text(stringResource(R.string.reduce))
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewCounterScreen() {
    // Preview for UI testing
    MaterialTheme {
        CounterComposable(
            state = CounterState(count = 5),
            onIncrementIntent = {}, // intents are not called in the preview
            onDecrementIntent = {}
        )
    }
}
