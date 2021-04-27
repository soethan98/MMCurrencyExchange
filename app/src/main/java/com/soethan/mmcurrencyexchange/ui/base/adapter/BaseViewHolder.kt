package com.soethan.mmcurrencyexchange.ui.base.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.soethan.mmcurrencyexchange.model.BaseUiModel
import com.soethan.mmcurrencyexchange.util.extension.clicks
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import timber.log.Timber

abstract class BaseViewHolder<T : BaseUiModel>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(data: T)
}