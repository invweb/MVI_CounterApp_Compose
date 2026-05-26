package com.example.counterappcompose

sealed class CounterIntent {
    data object Increment : CounterIntent() // The "Press +1" intent
    data object Decrement : CounterIntent() // The "Press -1" intent
}