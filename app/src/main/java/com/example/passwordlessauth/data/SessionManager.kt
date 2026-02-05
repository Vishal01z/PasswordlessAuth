package com.example.passwordlessauth.data

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SessionManager {

    private val _elapsedTime = MutableStateFlow(0L)
    val elapsedTime: StateFlow<Long> = _elapsedTime

    private var job: Job? = null

    fun startSession() {
        job?.cancel()
        job = CoroutineScope(Dispatchers.Default).launch {
            val startTime = System.currentTimeMillis()
            while (isActive) {
                delay(1000)
                _elapsedTime.value = System.currentTimeMillis() - startTime
            }
        }
    }

    fun stopSession() {
        job?.cancel()
        job = null
        _elapsedTime.value = 0L
    }
}
