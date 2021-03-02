package com.apro.mipsar2sens.ui.screens.main.di

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
        fun appRouter(router: AppRouter): Builder


        fun build(): MainScreenComponent
    }

    companion object {
        fun create() = with(DI.appComponent) {
            DaggerMainScreenComponent.builder()
                .appRouter(appRouter())
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