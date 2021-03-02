package com.apro.mipsar2sens.ui.screens.main.data

import android.app.Application
import com.ftdi.j2xx.D2xxManager
import com.ftdi.j2xx.D2xxManager.FtDeviceInfoListNode
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
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
        scope = CoroutineScope(CoroutineExceptionHandler { _, e -> Timber.e(e) })
        scope?.launch(Dispatchers.IO) {
            usbBroadcastReceiver.deviceConnectivityState().collect {
                println(">>> connected: $it")
                if (it) discoverDevice()
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
        println(">>> count: " + devCount)

        val deviceList = arrayOfNulls<FtDeviceInfoListNode>(devCount)
        d2xxManager.getDeviceInfoList(devCount, deviceList)

        deviceList.forEach {
            println(">>> it: " + it)
        }
        return devCount > 0

    }


    override fun reset() {
        usbBroadcastReceiver.reset()
        scope?.cancel()
        scope = null
    }
}