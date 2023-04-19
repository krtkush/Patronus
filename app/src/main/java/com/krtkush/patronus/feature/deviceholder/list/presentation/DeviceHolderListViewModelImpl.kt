package com.krtkush.patronus.feature.deviceholder.list.presentation

import androidx.core.app.NavUtils
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.krtkush.patronus.datasource.remote.rest.model.list.UserListResponse
import com.krtkush.patronus.domain.usecases.FetchUsersUseCase
import com.krtkush.patronus.feature.deviceholder.list.ui.DeviceHolderListFragmentDirections
import com.krtkush.patronus.feature.deviceholder.list.ui.DeviceHolderListViewModel
import com.krtkush.patronus.navigation.NavigationUtil
import com.krtkush.patronus.utils.network.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeviceHolderListViewModelImpl @Inject constructor(
    private val fetchUsersUseCase: FetchUsersUseCase,
    private val navigationUtil: NavigationUtil
    ) : DeviceHolderListViewModel, ViewModel() {

    private val _userListState = MutableStateFlow<NetworkResult<UserListResponse>>(NetworkResult.Loading)
    override val userListState: StateFlow<NetworkResult<UserListResponse>>
        get() = _userListState

    override fun fetchUsers() {

        _userListState.value = NetworkResult.Loading

        viewModelScope.launch {
            fetchUsersUseCase.invoke().collect {
                _userListState.value = it
            }
        }
    }

    override fun onUserSelected(userId: Int) {
        navigationUtil.navigateToDeviceHolderDetails(userId)
    }
}