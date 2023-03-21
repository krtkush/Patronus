package com.krtkush.patronus.feature.deviceholder.list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krtkush.patronus.data.models.users.list.UserListResponse
import com.krtkush.patronus.domain.usecases.FetchUsersUseCase
import com.krtkush.patronus.utils.network.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeviceHolderListViewModel @Inject constructor(private val fetchUsersUseCase: FetchUsersUseCase) : ViewModel() {

    private val _userListState = MutableStateFlow<NetworkResult<UserListResponse>>(NetworkResult.Loading)
    val userListState: StateFlow<NetworkResult<UserListResponse>>
        get() = _userListState

    fun fetchUsers() {

        _userListState.value = NetworkResult.Loading

        viewModelScope.launch {
            fetchUsersUseCase.invoke().collect {
                _userListState.value = it
            }
        }
    }
}