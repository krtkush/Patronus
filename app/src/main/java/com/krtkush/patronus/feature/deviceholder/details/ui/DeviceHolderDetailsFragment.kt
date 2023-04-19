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
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.krtkush.patronus.R
import com.krtkush.patronus.datasource.remote.rest.model.details.UserDetailsResponse
import com.krtkush.patronus.databinding.DeviceHolderDetailsFragmentBinding
import com.krtkush.patronus.feature.deviceholder.details.presentation.DeviceHolderDetailsViewModelImpl
import com.krtkush.patronus.utils.autoCleared
import com.krtkush.patronus.utils.network.NetworkResult
import com.krtkush.patronus.utils.ui.GlideRequestFailureListenerWrapper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DeviceHolderDetailsFragment : Fragment() {

    private val args : DeviceHolderDetailsFragmentArgs by navArgs()

    private val viewModel : DeviceHolderDetailsViewModelImpl by viewModels()
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
        viewModel.fetchUserDetails(args.userId)
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

        viewBinding.userFullName.text = "${response.firstName} ${response.lastName}"
        viewBinding.userGender.text = response.gender
        viewBinding.userPhoneNumber.text = response.phoneNumber
        viewBinding.userAddress.text = "${response.address.street}, " +
                "${response.address.city}, " +
                "${response.address.zip}, " +
                "${response.address.country}"

        if (response.stickers.contains("Fam")) {
            viewBinding.famTag.text = requireContext().getText(R.string.tag_text_fam)
            viewBinding.famTag.visibility = View.VISIBLE
        } else {
            viewBinding.famTag.visibility = View.GONE
        }

        if (response.stickers.contains("Ban")) {
            viewBinding.banTag.text = requireContext().getText(R.string.tag_text_ban)
            viewBinding.banTag.visibility = View.VISIBLE
        } else {
            viewBinding.banTag.visibility = View.GONE
        }

        viewBinding.imageAlternativeTV.visibility = View.GONE
        viewBinding.userImage.visibility = View.VISIBLE

        Glide.with(requireContext())
            .load(response.imageUrl)
            .listener(GlideRequestFailureListenerWrapper(
                onError = {
                    setImageFailAlternative(viewBinding, response)
                }
            ))
            .into(viewBinding.userImage)
    }

    private fun handleUserListFetchFail(message: String) {

        viewBinding.messageTV.visibility = View.VISIBLE
        viewBinding.messageTV.text = message
    }

    private fun setImageFailAlternative(
            itemBinding : DeviceHolderDetailsFragmentBinding,
            userItem : UserDetailsResponse
    ) {

        itemBinding.imageAlternativeTV.visibility = View.VISIBLE
        itemBinding.userImage.visibility = View.INVISIBLE
        itemBinding.imageAlternativeTV.text = "${userItem.firstName[0]}${userItem.lastName[0]}"
    }
}