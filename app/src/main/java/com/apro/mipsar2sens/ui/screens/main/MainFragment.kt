package com.apro.mipsar2sens.ui.screens.main


import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.apro.core.ui.BaseFragment
import com.apro.mipsar2sens.R
import com.apro.mipsar2sens.databinding.FragmentMainBinding
import com.apro.mipsar2sens.ui.common.BackButtonListener
import com.apro.mipsar2sens.ui.common.viewBinding
import com.apro.mipsar2sens.ui.screens.main.di.MainScreenComponent


class MainFragment : BaseFragment(R.layout.fragment_main), BackButtonListener {

    private lateinit var component: MainScreenComponent
    private val binding by viewBinding { FragmentMainBinding.bind(it) }
    private val viewModel by viewModels<MainScreenViewModel> { component.viewModelFactory() }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onBackPressed(): Boolean {
        requireActivity().finish()
        return true
    }

    companion object {
        fun create(component: MainScreenComponent) = MainFragment().apply {
            this.component = component
        }
    }

}