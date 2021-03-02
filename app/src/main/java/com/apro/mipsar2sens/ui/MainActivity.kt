package com.apro.mipsar2sens.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import com.apro.mipsar2sens.DI.appComponent
import com.apro.mipsar2sens.R
import com.apro.mipsar2sens.databinding.ActivityMainBinding
import com.apro.mipsar2sens.navigation.AppNavigator
import com.apro.mipsar2sens.ui.screens.Screens
import com.apro.mipsar2sens.ui.screens.main.MainScreenViewModel
import com.apro.mipsar2sens.ui.screens.main.di.MainScreenComponent

class MainActivity : AppCompatActivity() {

    lateinit var component: MainScreenComponent

    lateinit var binding: ActivityMainBinding

    private val viewModel by viewModels<MainScreenViewModel> { component.viewModelFactory() }

    private val navigator = AppNavigator(
        this,
        R.id.mainContainerView,
        supportFragmentManager,
        supportFragmentManager.fragmentFactory
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)



        appComponent.appRouter().newRootScreen(Screens.main())

    }

    override fun onResume() {
        super.onResume()
        appComponent.navigatorHolder().setNavigator(navigator)
    }

    override fun onPause() {
        appComponent.navigatorHolder().removeNavigator()
        super.onPause()
    }
}