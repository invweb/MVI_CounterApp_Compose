package com.example.counterappcompose

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.*

/**
 * ViewModel, который действует как Store в архитектуре MVI.
 * Он хранит текущее состояние (State) и обрабатывает входящие намерения (Intents).
 */
class CounterViewModel : ViewModel() {

    // 1. StateFlow: Текущее, наблюдаемое состояние системы.
    private val _state = MutableStateFlow(CounterState())
    val state: StateFlow<CounterState> = _state.asStateFlow()

    /**
     * 2. Функция-обработчик (Reducer): Главный метод MVI.
     * Принимает Intent и обновляет StateFlow, вызывая следующую итерацию состояния.
     * @param intent - Действие пользователя или системы.
     */
    fun handleIntent(intent: CounterIntent) {
        // Логика Reducer: (Текущее Состояние + Интент) -> Новое Состояние
        val newState = when (intent) {
            is CounterIntent.Increment -> {
                CounterState(count = _state.value.count + 1)
            }
            is CounterIntent.Decrement -> {
                // Добавим некоторую бизнес-логику: счетчик не может быть меньше нуля
                val newCount = if (_state.value.count > 0) _state.value.count - 1 else 0
                CounterState(count = newCount)
            }
        }

        // Обновляем состояние, вызывая реакцию во всех наблюдателях (View).
        _state.value = newState
    }
}