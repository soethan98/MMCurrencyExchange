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
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@FlowPreview
class RateCalculatorFragment constructor(private val viewModel: RateCalculatorViewModel) :
    DialogFragment() {
    private lateinit var args: RateCalculatorFragmentArgs

    private var _binding: FragmentRateCalculateDialogBinding? = null
    private val binding get() = _binding!!
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
        setUpUi()
        setTextChangeListener()
    }

    private fun setUpUi() {
        viewModel.rateCaLiveData.observe(viewLifecycleOwner,
            Observer {
                binding.tvLabelCurrency.text = it.code
                binding.tvCalculatedRate.text = it.calculatedAmount.toString()
                binding.tvLabelConvertedCurrency.text =
                    if (it.rateCalculationState == RateCalculationState.TO_KYAT) "MMK" else it.code
            })

    }

    private var textWatcher: TextWatcher? = null

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