package com.soethan.mmcurrencyexchange.ui.features.rate_list.otherbanks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.soethan.mmcurrencyexchange.R
import com.soethan.mmcurrencyexchange.databinding.FragmentOtherBankRateBinding
import com.soethan.mmcurrencyexchange.ui.base.BaseFragment


class OtherBankRateFragment : BaseFragment<FragmentOtherBankRateBinding>() {

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentOtherBankRateBinding.inflate(layoutInflater,container,false)


    override fun setUpToolbar() {
    }

    override fun onViewCreated(savedInstanceState: Bundle?) {
        super.onViewCreated(savedInstanceState)
    }


}