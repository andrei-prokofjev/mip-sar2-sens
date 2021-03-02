package com.apro.mipsar2sens.di

import android.app.Application
import com.apro.mipsar2sens.navigation.AppRouter
import com.apro.mipsar2sens.navigation.di.NavigationModule
import com.github.terrakok.cicerone.NavigatorHolder
import dagger.Component
import dagger.Module
import javax.inject.Singleton

@Component(modules = [AppModule::class, NavigationModule::class])
@Singleton
interface AppComponent {

    fun appRouter(): AppRouter

    fun navigatorHolder(): NavigatorHolder

    companion object {
        fun create(app: Application): AppComponent =
            DaggerAppComponent.builder()

                .build()
    }
}

@Module
class AppModule(private val app: Application) {

//
//  @Provides
//  @Singleton
//  fun resourceProvider(): ResourceProvider = AndroidResourceProvider(app)


}



