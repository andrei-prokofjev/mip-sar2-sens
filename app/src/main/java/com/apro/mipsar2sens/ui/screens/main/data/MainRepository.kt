package com.apro.mipsar2sens.ui.screens.main.data

import kotlinx.coroutines.flow.StateFlow

interface MainRepository {

    fun init()


    fun reset()


    fun appStateFlow(): StateFlow<AppState>
}