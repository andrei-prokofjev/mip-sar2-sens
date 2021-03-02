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

    private val _deviceState = MutableStateFlow(false)

    private var scope: CoroutineScope? = null

    override fun deviceConnectivityState() = _deviceState.asStateFlow()
    override fun init() {

    }


    private val broadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {
            scope?.launch(Dispatchers.IO) {
                _deviceState.emit(intent.action == UsbManager.ACTION_USB_DEVICE_ATTACHED)
            }
        }
    }

    init {
        scope = CoroutineScope(CoroutineExceptionHandler { _, e -> Timber.e(e) })

        val filter = IntentFilter()
        filter.addAction(UsbManager.ACTION_USB_DEVICE_ATTACHED)
        filter.addAction(UsbManager.ACTION_USB_DEVICE_DETACHED)
        app.registerReceiver(broadcastReceiver, filter)
    }


    override fun reset() {
        println(">>> unregister")
        app.unregisterReceiver(broadcastReceiver)
        scope?.cancel()
        scope = null
    }
}