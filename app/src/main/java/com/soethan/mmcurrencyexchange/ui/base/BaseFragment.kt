package com.soethan.mmcurrencyexchange.ui.base

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.soethan.mmcurrencyexchange.R

abstract class BaseFragment<VB : ViewBinding> : Fragment() {


    private var _binding: VB? = null// Binding variable to be used for accessing views.
    protected val binding
        get() = requireNotNull(_binding)

    abstract fun setupViewBinding(
        inflater: LayoutInflater, container: ViewGroup?
    ): VB


    final override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onViewCreated(savedInstanceState)
        setUpToolbar()
    }

    open fun onViewCreated(savedInstanceState: Bundle?) {}


    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = setupViewBinding(inflater, container)
        return requireNotNull(_binding).root
    }

    abstract fun setUpToolbar()
    open fun onClear() {}


    final override fun onDestroyView() {
        _binding = null
        onClear()
        super.onDestroyView()
    }


}