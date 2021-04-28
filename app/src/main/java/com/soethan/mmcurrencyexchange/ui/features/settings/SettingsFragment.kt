package com.soethan.mmcurrencyexchange.ui.features.settings


import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.soethan.mmcurrencyexchange.databinding.FragmentSettingsBinding
import com.soethan.mmcurrencyexchange.ui.base.BaseFragment
import com.soethan.mmcurrencyexchange.ui.main.MainActivity
import com.soethan.mmcurrencyexchange.util.extension.isNightMode
import com.soethan.mmcurrencyexchange.util.extension.restartActivity


class SettingsFragment constructor(private val viewModel: SettingsViewModel) :
    BaseFragment<FragmentSettingsBinding>() {

    override fun getViewBinding(): FragmentSettingsBinding {
        return FragmentSettingsBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.switchDarkTheme.isChecked = requireActivity().isNightMode()
        binding.switchDarkTheme.setOnCheckedChangeListener { _, _ ->
            viewModel.toggleNightMode()
            restartActivity()

        }
        binding.btnBack.setOnClickListener { findNavController().navigateUp() }
    }
    
    override fun setUpToolbar() {
        setHasOptionsMenu(false)
    }

}