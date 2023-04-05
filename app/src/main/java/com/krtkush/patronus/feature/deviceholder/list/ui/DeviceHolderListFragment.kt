package com.krtkush.patronus.feature.deviceholder.list.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.krtkush.patronus.R
import com.krtkush.patronus.datasource.remote.rest.model.list.Customer
import com.krtkush.patronus.datasource.remote.rest.model.list.UserListResponse
import com.krtkush.patronus.databinding.DeviceHolderListFragmentBinding
import com.krtkush.patronus.feature.deviceholder.details.ui.userIdKey
import com.krtkush.patronus.feature.deviceholder.list.presentation.DeviceHolderListViewModelImpl
import com.krtkush.patronus.main.base.BaseFragment
import com.krtkush.patronus.utils.autoCleared
import com.krtkush.patronus.utils.network.NetworkResult
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DeviceHolderListFragment : BaseFragment<DeviceHolderListFragmentBinding>(
    DeviceHolderListFragmentBinding::inflate
), DeviceHolderListAdapter.DeviceHolderItemOnClickListener {

    private val viewModel : DeviceHolderListViewModelImpl by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataObservers()
        viewModel.fetchUsers()
    }

    private fun dataObservers() {

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.userListState.collect {
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
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun handleUserListFetchSuccess(response : UserListResponse) {

        binding.listRV.visibility = View.VISIBLE
        binding.messageTV.visibility = View.GONE

        setupAndPopulateUserListRV(response.customers)
    }

    private fun handleUserListFetchFail(message: String) {

        binding.listRV.visibility = View.GONE
        binding.messageTV.visibility = View.VISIBLE
        binding.messageTV.text = message
    }

    private fun setupAndPopulateUserListRV(users: List<Customer>) {

        val deviceHolderListAdapter
            = DeviceHolderListAdapter(this, users)
        val dividerItemDecoration
            = DividerItemDecoration(binding.listRV.context, DividerItemDecoration.VERTICAL)
        ResourcesCompat.getDrawable(resources, R.drawable.eighty_percent_div, null)
            ?.let { dividerItemDecoration.setDrawable(it) }

        binding.listRV.layoutManager = LinearLayoutManager(requireContext())
        binding.listRV.adapter = deviceHolderListAdapter
        binding.listRV.addItemDecoration(dividerItemDecoration)
    }

    override fun onDeviceHolderItemSelected(userId: Int) {
        viewModel.onUserSelected(userId)

        val action = DeviceHolderListFragmentDirections.actionGlobalGotoDeviceHolderDetailsFragment(userId)
        findNavController().navigate(action)
    }
}
