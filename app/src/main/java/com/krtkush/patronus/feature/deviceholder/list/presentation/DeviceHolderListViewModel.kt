package com.krtkush.patronus.feature.deviceholder.list.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krtkush.patronus.data.models.UserListResponseModel
import com.krtkush.patronus.domain.usecases.GetUsersUseCase
import com.krtkush.patronus.utils.network.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeviceHolderListViewModel @Inject constructor(private val getUsersUseCase: GetUsersUseCase) : ViewModel() {

    private val _userListState = MutableStateFlow<NetworkResult<UserListResponseModel>>(NetworkResult.Loading)
    val userListState: StateFlow<NetworkResult<UserListResponseModel>>
        get() = _userListState

    fun getUsers() {

        _userListState.value = NetworkResult.Loading

        viewModelScope.launch {
            getUsersUseCase.invoke().collect {
                _userListState.value = it
            }
        }
    }
}