package com.apro.mipsar2sens.ui.screens

import com.apro.mipsar2sens.ui.screens.main.MainFragment
import com.apro.mipsar2sens.ui.screens.main.di.MainScreenComponent
import com.github.terrakok.cicerone.androidx.FragmentScreen


object Screens {
    fun main() = FragmentScreen(MainFragment::javaClass.name) {
        MainFragment.create(MainScreenComponent.create())
    }


}