package com.krtkush.patronus.feature.deviceholder.details.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krtkush.patronus.data.models.user.details.UserDetailsResponse
import com.krtkush.patronus.data.models.user.list.UserListResponse
import com.krtkush.patronus.domain.usecases.FetchUserDetailsByIdUseCase
import com.krtkush.patronus.domain.usecases.FetchUsersUseCase
import com.krtkush.patronus.utils.network.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeviceHolderDetailsViewModel @Inject constructor(private val fetchUserDetailsByIdUseCase: FetchUserDetailsByIdUseCase) : ViewModel() {

    private val _userDetailsState = MutableStateFlow<NetworkResult<UserDetailsResponse>>(NetworkResult.Loading)
    val userDetailsState: StateFlow<NetworkResult<UserDetailsResponse>>
        get() = _userDetailsState

    fun fetchUserDetails(id: Int) {

        _userDetailsState.value = NetworkResult.Loading

        viewModelScope.launch {
            fetchUserDetailsByIdUseCase.invoke(id).collect {
                _userDetailsState.value = it
            }
        }
    }
}