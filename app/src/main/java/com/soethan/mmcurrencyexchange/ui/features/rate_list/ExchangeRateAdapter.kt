package com.soethan.mmcurrencyexchange.ui.features.rate_list

import android.view.LayoutInflater
import android.view.ViewGroup
import com.soethan.mmcurrencyexchange.R
import com.soethan.mmcurrencyexchange.databinding.ItemExchangeRateBinding
import com.soethan.mmcurrencyexchange.model.ExchangeRateUiModel
import com.soethan.mmcurrencyexchange.ui.base.adapter.BaseAdapter
import com.soethan.mmcurrencyexchange.ui.base.adapter.ClickListener
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber

class ExchangeRateAdapter(private val clickListener: ClickListener<ExchangeRateUiModel>) :
    BaseAdapter<ExchangeRateUiModel, ExchangeRateViewHolder>() {
    override fun onBindViewHolder(holder: ExchangeRateViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExchangeRateViewHolder {
        val binding =
            ItemExchangeRateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExchangeRateViewHolder(binding, clickListener = clickListener)
    }
}

