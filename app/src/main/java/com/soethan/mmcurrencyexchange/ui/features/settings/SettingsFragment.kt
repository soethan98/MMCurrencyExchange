package com.soethan.mmcurrencyexchange.ui.features.settings


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.soethan.mmcurrencyexchange.databinding.FragmentSettingsBinding
import com.soethan.mmcurrencyexchange.ui.base.BaseFragment
import com.soethan.mmcurrencyexchange.ui.main.MainActivity
import com.soethan.mmcurrencyexchange.util.extension.isNightMode
import com.soethan.mmcurrencyexchange.util.extension.restartActivity


class SettingsFragment constructor(private val viewModel: SettingsViewModel) :
    BaseFragment<FragmentSettingsBinding>() {

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSettingsBinding {
        return FragmentSettingsBinding.inflate(layoutInflater,container,false)

    }

    override fun onViewCreated(savedInstanceState: Bundle?) {
        super.onViewCreated(savedInstanceState)
        binding.switchDarkTheme.isChecked = requireActivity().isNightMode()
        binding.switchDarkTheme.setOnCheckedChangeListener { _, _ ->
            viewModel.toggleNightMode()
            restartActivity()

        }
    }

    override fun setUpToolbar() {
        setHasOptionsMenu(false)
    }


}