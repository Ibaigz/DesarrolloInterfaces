package com.example.myapplication

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State

class ContadorViewModel : ViewModel() {
    // Estado del contador
    private val _counter = mutableStateOf(0)
    val contador: State<Int> = _counter

    // Función para incrementar el contador
    fun incrementCounter() {
        _counter.value += 1
    }

    fun decrementCounter() {
        _counter.value -= 1

    }
}