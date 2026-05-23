package com.example.counterappcompose

sealed class CounterIntent {
    data object Increment : CounterIntent() // Интент "Нажать +1"
    data object Decrement : CounterIntent() // Интент "Нажать -1"
}