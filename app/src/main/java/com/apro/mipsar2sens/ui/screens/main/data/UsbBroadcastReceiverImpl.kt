package com.apro.mipsar2sens.ui.screens.main.data

import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.hardware.usb.UsbManager
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import timber.log.Timber
import javax.inject.Inject

class UsbBroadcastReceiverImpl @Inject constructor(
    val app: Application
) : UsbBroadcastReceiver {

    private val _state = MutableStateFlow(false)

    private var scope: CoroutineScope? = null

    override fun deviceConnectivityState() = _state.asStateFlow()


    private val broadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {
            scope?.launch(Dispatchers.IO) {
                when (intent.action) {
                    UsbManager.ACTION_USB_DEVICE_ATTACHED -> {
                        _state.emit(true)
                    }
                    UsbManager.ACTION_USB_ACCESSORY_DETACHED -> {
                        _state.emit(false)
                    }
                }
            }
        }
    }

    init {
        reset()
        scope = CoroutineScope(CoroutineExceptionHandler { _, e -> Timber.e(e) })

        val filter = IntentFilter()
        filter.addAction(UsbManager.ACTION_USB_DEVICE_ATTACHED)
        filter.addAction(UsbManager.ACTION_USB_DEVICE_DETACHED)
        app.registerReceiver(broadcastReceiver, filter)
    }


    override fun reset() {
        app.unregisterReceiver(broadcastReceiver)
        scope?.cancel()
        scope = null
    }
}