package com.apro.mipsar2sens.ui.screens.main.data

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
) : MainRepository {


    private var scope: CoroutineScope? = null

    override fun init() {
        reset()
        scope = CoroutineScope(CoroutineExceptionHandler { _, e -> Timber.e(e) })

        scope?.launch {

        }
    }


    override fun reset() {
        scope?.cancel()
        scope = null
    }
}