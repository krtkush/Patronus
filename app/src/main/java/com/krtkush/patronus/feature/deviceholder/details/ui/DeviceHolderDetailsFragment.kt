package com.krtkush.patronus.feature.deviceholder.details.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.krtkush.patronus.databinding.DeviceHolderDetailsFragmentBinding
import com.krtkush.patronus.feature.deviceholder.details.presentation.DeviceHolderDetailsViewModel
import com.krtkush.patronus.utils.autoCleared

class DeviceHolderDetailsFragment : Fragment() {

    private val viewModel : DeviceHolderDetailsViewModel by viewModels()
    private var viewBinding : DeviceHolderDetailsFragmentBinding  by autoCleared()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = DeviceHolderDetailsFragmentBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
    }

    private fun setupObservers() {

    }
}