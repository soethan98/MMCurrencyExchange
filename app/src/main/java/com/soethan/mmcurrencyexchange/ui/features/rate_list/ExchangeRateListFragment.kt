package com.soethan.mmcurrencyexchange.ui.features.rate_list

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.soethan.mmcurrencyexchange.ui.base.BaseFragment
import com.soethan.mmcurrencyexchange.databinding.FragmentExchangeRateListBinding
import com.soethan.mmcurrencyexchange.util.Resource
import com.soethan.mmcurrencyexchange.util.extension.hide
import com.soethan.mmcurrencyexchange.util.extension.show
import com.soethan.mmcurrencyexchange.util.extension.toast
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

class ExchangeRateListFragment constructor(private val viewModel: ExchangeRateViewModel) :
    BaseFragment<FragmentExchangeRateListBinding>() {


    private val TAG = "ExchangeRateListFragment"

    private val exchangeRateAdapter:ExchangeRateAdapter by lazy { ExchangeRateAdapter{
        requireContext().toast(it.countryName)

    } }


    override fun getViewBinding(): FragmentExchangeRateListBinding {
        return FragmentExchangeRateListBinding.inflate(layoutInflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getExchangeRate()
        setupRecyclerView()
        setSwipeRefreshListener()
        observeData()

    }


    private fun setupRecyclerView() {
        binding.rvExchangeList.layoutManager = LinearLayoutManager(requireContext())
        binding.rvExchangeList.adapter = exchangeRateAdapter
        binding.rvExchangeList.setHasFixedSize(true)
    }

    private fun setSwipeRefreshListener() {
        binding.swipeRefreshList.setOnRefreshListener {
            binding.swipeRefreshList.isRefreshing = true
            viewModel.getExchangeRate(isForceRefresh = true)
            lifecycleScope.launch {
                delay(4_000)
                binding.swipeRefreshList.isRefreshing = false
            }
        }

    }

    private fun observeData() {
        viewModel.exchangeRateLiveData.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {
                    binding.progressExchangeList.show()
                }
                is Resource.Content -> {
                    binding.progressExchangeList.hide()
                    Timber.i("$TAG %s", it.content)
                    binding.itemUpdateTimeBanner.tvLastUpdateTime.text = "Last Update on - ${it.content.lastUpdateTime}"
                    exchangeRateAdapter.submitList(it.content.exchangeRateUiModel)

                }
                is Resource.Error -> {
                    binding.progressExchangeList.hide()
                    requireContext().toast(it.throwable.message)

                }

            }
        })

    }


}