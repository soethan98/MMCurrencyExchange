package com.soethan.mmcurrencyexchange.ui.features.splash

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.color.MaterialColors
import com.soethan.mmcurrencyexchange.R
import com.soethan.mmcurrencyexchange.databinding.FragmentSplashScreenBinding
import com.soethan.mmcurrencyexchange.ui.base.BaseFragment
import com.soethan.mmcurrencyexchange.util.extension.getApplicationVersionName
import kotlinx.coroutines.delay

class SplashScreenFragment : BaseFragment<FragmentSplashScreenBinding>() {

    private var DURATION = 2000L


    override fun onViewCreated(savedInstanceState: Bundle?) {
        super.onViewCreated(savedInstanceState)
        changeSplashStatusBarColor()
        showApplicationVersion()
        lifecycleScope.launchWhenStarted {
            delay(DURATION)
            findNavController().popBackStack()
            findNavController().navigate(R.id.exchangeRateListFragment)

        }
    }

    private fun showApplicationVersion(){
        binding.tvVersionName.text = getString(R.string.version_name,requireContext().getApplicationVersionName())
    }

    private fun changeSplashStatusBarColor() {
        val window = requireActivity().window
        window.statusBarColor = ContextCompat.getColor(requireContext(), R.color.secondaryLightColor)
    }

    private fun restoreNormalStatusBarColor(){
        requireActivity().window.statusBarColor = MaterialColors.getColor(requireView(),R.attr.colorPrimaryVariant)
    }

    override fun setUpToolbar() {
        setHasOptionsMenu(false)
    }

    override fun onClear() {
        restoreNormalStatusBarColor()
    }

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSplashScreenBinding {
        return FragmentSplashScreenBinding.inflate(layoutInflater,container,false)
    }

}