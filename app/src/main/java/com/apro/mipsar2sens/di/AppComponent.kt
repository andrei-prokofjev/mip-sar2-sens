package com.apro.mipsar2sens.di

import android.app.Application
import com.apro.mipsar2sens.navigation.AppRouter
import com.apro.mipsar2sens.navigation.di.NavigationModule
import com.apro.mipsar2sens.ui.screens.main.data.UsbBroadcastReceiver
import com.apro.mipsar2sens.ui.screens.main.data.UsbBroadcastReceiverImpl
import com.ftdi.j2xx.D2xxManager
import com.github.terrakok.cicerone.NavigatorHolder
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Component(modules = [AppModule::class, NavigationModule::class])
@Singleton
interface AppComponent {

    fun app(): Application

    fun appRouter(): AppRouter

    fun navigatorHolder(): NavigatorHolder

    fun usbBroadcastReceiver(): UsbBroadcastReceiver

    fun d2xxManager(): D2xxManager

    companion object {
        fun create(app: Application): AppComponent =
            DaggerAppComponent.builder()
                .appModule(AppModule(app))
                .build()
    }
}

@Module
class AppModule(private val app: Application) {

//
//  @Provides
//  @Singleton
//  fun resourceProvider(): ResourceProvider = AndroidResourceProvider(app)

    @Provides
    @Singleton
    fun usbBroadcastReceiverProvider(): UsbBroadcastReceiver = UsbBroadcastReceiverImpl(app)

    @Provides
    @Singleton
    fun d2xxManager(): D2xxManager = D2xxManager.getInstance(app)

    @Provides
    @Singleton
    fun app(): Application = app


}



