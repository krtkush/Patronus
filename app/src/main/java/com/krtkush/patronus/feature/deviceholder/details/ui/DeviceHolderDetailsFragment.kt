package com.krtkush.patronus.feature.deviceholder.details.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.krtkush.patronus.data.models.user.details.UserDetailsResponse
import com.krtkush.patronus.data.models.user.list.UserListResponse
import com.krtkush.patronus.databinding.DeviceHolderDetailsFragmentBinding
import com.krtkush.patronus.feature.deviceholder.details.presentation.DeviceHolderDetailsViewModel
import com.krtkush.patronus.utils.autoCleared
import com.krtkush.patronus.utils.network.NetworkResult
import kotlinx.coroutines.launch

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

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.userDetailsState.collect {
                    it.let { response ->

                        when(response) {

                            is NetworkResult.Loading -> {
                                toggleProgressBarVisibility(true)
                            }

                            is NetworkResult.Success -> {
                                toggleProgressBarVisibility(false)
                                handleUserListFetchSuccess(response.data)
                            }

                            is NetworkResult.Error -> {
                                toggleProgressBarVisibility(false)
                                handleUserListFetchFail(response.message)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun toggleProgressBarVisibility(show : Boolean) {

        if (show) {
            viewBinding.progressBar.visibility = View.VISIBLE
        } else {
            viewBinding.progressBar.visibility = View.GONE
        }
    }

    private fun handleUserListFetchSuccess(response : UserDetailsResponse) {

        viewBinding.messageTV.visibility = View.GONE
    }

    private fun handleUserListFetchFail(message: String) {

        viewBinding.messageTV.visibility = View.VISIBLE
        viewBinding.messageTV.text = message
    }
}