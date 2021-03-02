package com.apro.mipsar2sens.ui.screens.main.data

import android.app.Application
import com.ftdi.j2xx.D2xxManager
import com.ftdi.j2xx.D2xxManager.FtDeviceInfoListNode
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val usbBroadcastReceiver: UsbBroadcastReceiver,
    private val d2xxManager: D2xxManager,
    private val app: Application
) : MainRepository {



    private val _appState = MutableStateFlow(AppState.Idle)

    private var scope: CoroutineScope? = null

    override fun init() {
        reset()
        scope = CoroutineScope(CoroutineExceptionHandler { _, e -> Timber.e(e) })

        scope?.launch {
            usbBroadcastReceiver.deviceConnectivityState().collect {
                println(">>> connected: $it")
            }


        }
    }

    override fun appStateFlow() = _appState.asStateFlow()

    /**
     * Looks up for the device info list using the D2xxManager to check if any USB device is connected.
     * @return A boolean indicating if any device is found
     */
    private fun discoverDevice(): Boolean {

        val devCount = d2xxManager.createDeviceInfoList(app)

        val deviceList = arrayOfNulls<FtDeviceInfoListNode>(devCount)
        d2xxManager.getDeviceInfoList(devCount, deviceList)
        return devCount > 0

    }


    override fun reset() {
        usbBroadcastReceiver.reset()
        scope?.cancel()
        scope = null
    }
}