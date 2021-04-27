package com.soethan.mmcurrencyexchange.ui.base.adapter

import androidx.recyclerview.widget.ListAdapter
import com.soethan.mmcurrencyexchange.model.BaseUiModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.asFlow

abstract class BaseAdapter<T : BaseUiModel, H : BaseViewHolder<T>> :
    ListAdapter<T, H>(BaseDiffCallback())

typealias  ClickListener<T> = (T) -> Unit