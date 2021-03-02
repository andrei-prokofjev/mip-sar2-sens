package com.apro.mipsar2sens.ui.screens.main.data

import kotlinx.coroutines.flow.StateFlow

interface UsbBroadcastReceiver {

   fun reset()
   fun deviceConnectivityState(): StateFlow<Boolean>
   fun init()
}