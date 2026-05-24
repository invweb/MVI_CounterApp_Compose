package com.example.counterappcompose

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.*

/**
 * A ViewModel that acts as a Store in the MVI architecture.
 * It stores the current state (State) and handles incoming intentions (Intents).
 */
class CounterViewModel : ViewModel() {

    // 1. StateFlow: The current, observable state of the system.
    private val _state = MutableStateFlow(CounterState())
    val state: StateFlow<CounterState> = _state.asStateFlow()

    /**
     * 2. Handler function (Reducer): The main method of MVI.
     * Accepts an Intent and updates the StateFlow, triggering the next iteration of the state.
     * @param intent - User or system action.
     */
    fun handleIntent(intent: CounterIntent) {
        // Reducer Logic: (Current State + Intent) -> New State
        val newState = when (intent) {
            is CounterIntent.Increment -> {
                CounterState(count = _state.value.count + 2)
            }
            is CounterIntent.Decrement -> {
                // Add some business logic: the counter cannot be less than zero
                val newCount = if (_state.value.count >= 5) _state.value.count - 5 else 0
                CounterState(count = newCount)
            }
        }

        // Update the state, triggering a reaction in all observers (View).
        _state.value = newState
    }
}