package com.example.counterappcompose

data class CounterState(
    val count: Int = 0,
    val isLoading: Boolean = false // Adding a flag for a better example
)