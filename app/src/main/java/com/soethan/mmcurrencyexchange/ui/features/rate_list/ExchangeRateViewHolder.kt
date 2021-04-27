package com.soethan.mmcurrencyexchange.ui.features.rate_list

import androidx.core.content.ContextCompat
import com.soethan.mmcurrencyexchange.databinding.ItemExchangeRateBinding
import com.soethan.mmcurrencyexchange.model.ExchangeRateUiModel
import com.soethan.mmcurrencyexchange.ui.base.adapter.BaseViewHolder
import com.soethan.mmcurrencyexchange.ui.base.adapter.ClickListener
import com.soethan.mmcurrencyexchange.util.getFlagIcon
import kotlinx.coroutines.ExperimentalCoroutinesApi
import timber.log.Timber

class ExchangeRateViewHolder(private var binding: ItemExchangeRateBinding,private var clickListener: ClickListener<ExchangeRateUiModel>) :
    BaseViewHolder<ExchangeRateUiModel>(binding.root) {

    override fun bind(data: ExchangeRateUiModel) {
        binding.tvCurrency.text = data.code
        binding.tvRate.text = data.rate
        binding.tvCountryName.text = data.countryName
        binding.imgFlag.setImageDrawable(ContextCompat.getDrawable(itemView.context, getFlagIcon(data.code)))

        binding.root.setOnClickListener { clickListener(data)  }
    }
}