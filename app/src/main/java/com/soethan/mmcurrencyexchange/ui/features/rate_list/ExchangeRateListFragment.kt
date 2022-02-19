package com.soethan.mmcurrencyexchange.ui.features.rate_list

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.soethan.domain.util.Result
import com.soethan.domain.util.data
import com.soethan.domain.util.getDataOrThrow
import com.soethan.mmcurrencyexchange.R
import com.soethan.mmcurrencyexchange.ui.base.BaseFragment
import com.soethan.mmcurrencyexchange.databinding.FragmentExchangeRateListBinding
import com.soethan.mmcurrencyexchange.util.extension.hide
import com.soethan.mmcurrencyexchange.util.extension.show
import com.soethan.mmcurrencyexchange.util.extension.toast
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ExchangeRateListFragment constructor(private val viewModel: ExchangeRateViewModel) :
    BaseFragment<FragmentExchangeRateListBinding>() {


    private val TAG = "ExchangeRateListFragment"

    private val exchangeRateAdapter: ExchangeRateAdapter by lazy {
        ExchangeRateAdapter {
            val action =
                ExchangeRateListFragmentDirections.actionExchangeRateListFragmentToRateCalculatorFragment2(
                    it.id
                )
            findNavController().navigate(action)
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.onStart()
    }

    override fun setupViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentExchangeRateListBinding {
        return FragmentExchangeRateListBinding.inflate(layoutInflater, container, false)
    }

    override fun onViewCreated(savedInstanceState: Bundle?) {
        super.onViewCreated(savedInstanceState)
        //viewModel.getExchangeRate()
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
        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = true
            viewModel.onManualRefresh()
            lifecycleScope.launch {
                delay(4_000)
                binding.swipeRefreshLayout.isRefreshing = false
            }
        }

    }

    private fun observeData() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.getExchangeRate.collect { result ->
                result ?: return@collect
                when (result) {
                    is Result.Loading -> binding.progressExchangeList.show()
                    is Result.Error -> {
                        binding.progressExchangeList.hide()
                        requireContext().toast(result.throwable.message)
                    }

                    is Result.Success -> {
                        binding.progressExchangeList.hide()
                        binding.rvExchangeList.show()
                        binding.itemUpdateTimeBanner.tvLastUpdateTime.text =
                            getString(R.string.last_updated_time, result.data.lastUpdateTime)
                        exchangeRateAdapter.submitList(result.data.exchangeRateUiModel)
                    }

                }

            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.events.collect { event ->
                when (event) {
                    is Event.ShowErrorMessage -> {
                        binding.progressExchangeList.hide()
                        requireContext().toast(event.error.message)
                    }

                }

            }
        }

    }


    override fun setUpToolbar() {
        setHasOptionsMenu(true)
        (activity as? AppCompatActivity)?.setSupportActionBar(binding.toolbar)
    }


}