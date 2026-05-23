package com.example.counterappcompose

data class CounterState(
    val count: Int = 0,
    val isLoading: Boolean = false // Добавляем флаг для лучшего примера
)