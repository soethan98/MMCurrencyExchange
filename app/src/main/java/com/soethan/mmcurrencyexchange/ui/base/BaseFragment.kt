package com.soethan.mmcurrencyexchange.ui.base

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.soethan.mmcurrencyexchange.R

abstract class BaseFragment<VB : ViewBinding> : Fragment() {

  
    protected lateinit var binding: VB
    protected abstract fun getViewBinding(): VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()

    }

    private fun init() {
        binding = getViewBinding()
        setUpToolbar()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    abstract fun setUpToolbar()



}