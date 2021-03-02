package com.apro.mipsar2sens.navigation


import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.FragmentManager
import com.github.terrakok.cicerone.Command

class AppNavigator constructor(
  private val fragmentActivity: FragmentActivity,
  containerId: Int,
  fragmentManager: FragmentManager = fragmentActivity.supportFragmentManager,
  fragmentFactory: FragmentFactory = FragmentFactory()
) : com.github.terrakok.cicerone.androidx.AppNavigator(
  fragmentActivity,
  containerId,
  fragmentManager,
  fragmentFactory
) {

    override fun applyCommand(command: Command) {
        super.applyCommand(command)

        when (command) {
            //is ModalDialog -> fragmentActivity.modal(command.fragment)
        }
    }
}