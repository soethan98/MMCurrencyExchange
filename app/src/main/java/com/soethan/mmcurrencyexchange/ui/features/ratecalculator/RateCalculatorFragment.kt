package com.soethan.mmcurrencyexchange.ui.features.ratecalculator

import android.os.Bundle
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.soethan.mmcurrencyexchange.R
import com.soethan.mmcurrencyexchange.databinding.FragmentRateCalculateDialogBinding
import com.soethan.mmcurrencyexchange.model.RateCalculationState
import com.soethan.mmcurrencyexchange.util.extension.toast
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.*

@ExperimentalCoroutinesApi
@FlowPreview
class RateCalculatorFragment( val viewModel: RateCalculatorViewModel) :
    DialogFragment() {

    private var _binding: FragmentRateCalculateDialogBinding? = null
    private val binding get() = _binding!!

    private var textWatcher: TextWatcher? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        dialog?.window?.attributes?.windowAnimations = R.style.DialogTransition
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }


    override fun onDestroyView() {
        _binding = null
        textWatcher = null
        super.onDestroyView()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRateCalculateDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = RateCalculatorFragmentArgs.fromBundle(requireArguments()).id
        viewModel.getExchangeRateFromDb(id)
        viewModel.getCalculationState()
        setUpUi()
        setTextChangeListener()

    }

    private fun setUpUi() {
        binding.btnConvertCurrency.setOnClickListener {
            viewModel.reverseCurrencyCode()
            binding.edtRate.text.clear()
        }
        viewModel.rateCaLiveData.observe(viewLifecycleOwner, Observer {
            binding.tvCalculatedRate.text = it.calculatedAmount
            when (it.rateCalculationState) {
                RateCalculationState.TO_KYAT -> {
                    binding.tvLabelCurrency.text = it.code
                    binding.tvLabelConvertedCurrency.text = "MMK"
                }
                RateCalculationState.FROM_KYAT -> {
                    binding.tvLabelCurrency.text = "MMK"
                    binding.tvLabelConvertedCurrency.text = it.code
                }
            }
        })
        viewModel.throwableLiveData.observe(viewLifecycleOwner, Observer {
            requireContext().toast(it.message ?: "Something went wrong")
        })
    }


    private fun setTextChangeListener() {
        textWatcher = binding.edtRate.addTextChangedListener {
            submitBaseAmountValue(it.toString())
        }
    }

    private fun submitBaseAmountValue(value: String) {
        lifecycleScope.launch {
            viewModel.amount.value = value
        }
    }


}