package com.krtkush.patronus.feature.deviceholder.list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krtkush.patronus.datasource.remote.rest.model.list.UserListResponse
import com.krtkush.patronus.domain.usecases.FetchUsersUseCaseImpl
import com.krtkush.patronus.feature.deviceholder.list.ui.DeviceHolderListViewModel
import com.krtkush.patronus.utils.network.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeviceHolderListViewModelImpl @Inject constructor(
        private val fetchUsersUseCaseImpl: FetchUsersUseCaseImpl
    ) : DeviceHolderListViewModel, ViewModel() {

    private val _userListState = MutableStateFlow<NetworkResult<UserListResponse>>(NetworkResult.Loading)
    override val userListState: StateFlow<NetworkResult<UserListResponse>>
        get() = _userListState

    override fun fetchUsers() {

        _userListState.value = NetworkResult.Loading

        viewModelScope.launch {
            fetchUsersUseCaseImpl.invoke().collect {
                _userListState.value = it
            }
        }
    }
}