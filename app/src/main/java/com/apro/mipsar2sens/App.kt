package com.apro.mipsar2sens

import android.app.Application
import com.apro.mipsar2sens.di.AppComponent
import com.apro.mipsar2sens.di.DaggerAppComponent
import timber.log.Timber


class App : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        DI.appComponent = AppComponent.create(this)


    }
}