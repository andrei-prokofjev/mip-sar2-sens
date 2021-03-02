package com.apro.mipsar2sens.ui.screens.main

import android.os.Handler
import androidx.lifecycle.viewModelScope
import com.apro.core.ui.BaseViewModel
import com.apro.mipsar2sens.navigation.AppRouter
import com.apro.mipsar2sens.ui.screens.main.business.MainInteractor
import com.apro.mipsar2sens.ui.screens.main.data.UsbBroadcastReceiver
import com.ftdi.j2xx.FT_Device
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainScreenViewModel @Inject constructor(
    private val appRouter: AppRouter,
    private val mainInteractor: MainInteractor,


) : BaseViewModel() {



    init {

        mainInteractor.init()
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
           // usbBroadcastReceiver.deviceConnectivityState().collect {

            //}
        }


    }

//    /**
//     * Writes the input data to the USB device
//     * @param script MethodSCRIPT to be written
//     * @return A boolean indicating if the write operation succeeded.
//     */
//    @Synchronized
//    public fun writeToDevice(script: String): Boolean {
//        ftDevice?.let {
//            if (it.isOpen) {
//                return it.write(script.toByteArray()) == script.length
//            }
//        }
//        return false
//    }
//
//
//    fun sendVersionCmd() {
//        //Send newline to clear command buf on pico, in case there was invalid data in it
//        writeToDevice("\n")
//
//        writeToDevice(CMD_VERSION_STRING)
//    }


}