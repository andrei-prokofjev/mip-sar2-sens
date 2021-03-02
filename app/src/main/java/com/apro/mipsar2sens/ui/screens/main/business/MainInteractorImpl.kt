package com.apro.mipsar2sens.ui.screens.main.business

import com.apro.mipsar2sens.ui.screens.main.data.MainRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import javax.inject.Inject

class MainInteractorImpl @Inject constructor(
    private val mainRepository: MainRepository
) : MainInteractor {

    private var scope: CoroutineScope? = null

    override fun init() {
        reset()
        scope = CoroutineScope(Dispatchers.IO)
        mainRepository.init()
    }


    override fun reset() {
        scope?.cancel()
        mainRepository.reset()
    }
}