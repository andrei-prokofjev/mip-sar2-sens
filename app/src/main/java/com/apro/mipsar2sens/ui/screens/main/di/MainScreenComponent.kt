package com.apro.mipsar2sens.ui.screens.main.di

import android.app.Application
import androidx.lifecycle.ViewModel
import com.apro.mipsar2sens.DI
import com.apro.mipsar2sens.di.ViewModelFactory
import com.apro.mipsar2sens.di.ViewModelKey
import com.apro.mipsar2sens.navigation.AppRouter
import com.apro.mipsar2sens.ui.screens.main.MainScreenViewModel
import com.apro.mipsar2sens.ui.screens.main.business.MainInteractor
import com.apro.mipsar2sens.ui.screens.main.business.MainInteractorImpl
import com.apro.mipsar2sens.ui.screens.main.data.MainRepository
import com.apro.mipsar2sens.ui.screens.main.data.MainRepositoryImpl
import com.apro.mipsar2sens.ui.screens.main.data.UsbBroadcastReceiver
import com.ftdi.j2xx.D2xxManager
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.multibindings.IntoMap

@Component(modules = [MainScreenModule::class])
interface MainScreenComponent {

    fun viewModelFactory(): ViewModelFactory

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun app(app: Application): Builder

        @BindsInstance
        fun appRouter(router: AppRouter): Builder

        @BindsInstance
        fun usbBroadcastReceiver(usbBroadcastReceiver: UsbBroadcastReceiver): Builder

        @BindsInstance
        fun deviceManager(d2xxManager: D2xxManager): Builder


        fun build(): MainScreenComponent
    }

    companion object {
        fun create() = with(DI.appComponent) {
            DaggerMainScreenComponent.builder()
                .app(app())
                .appRouter(appRouter())
                .usbBroadcastReceiver(usbBroadcastReceiver())
                .deviceManager(d2xxManager())
                .build()
        }
    }
}

@Module
abstract class MainScreenModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainScreenViewModel::class)
    abstract fun mainScreenViewModel(viewModel: MainScreenViewModel): ViewModel

    @Binds
    abstract fun mainInteractor(interactor: MainInteractorImpl): MainInteractor

    @Binds
    abstract fun mainRepository(repository: MainRepositoryImpl): MainRepository
}