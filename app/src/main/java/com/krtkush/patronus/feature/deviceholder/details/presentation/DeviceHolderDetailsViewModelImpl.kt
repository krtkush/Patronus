package com.krtkush.patronus.feature.deviceholder.details.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krtkush.patronus.datasource.remote.rest.model.details.UserDetailsResponse
import com.krtkush.patronus.domain.usecases.FetchUserDetailsByIdUseCase
import com.krtkush.patronus.feature.deviceholder.details.ui.DeviceHolderDetailsViewModel
import com.krtkush.patronus.utils.network.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeviceHolderDetailsViewModelImpl @Inject constructor(
        private val fetchUserDetailsByIdUseCase: FetchUserDetailsByIdUseCase
    ) : DeviceHolderDetailsViewModel, ViewModel() {

    private val _userDetailsState = MutableStateFlow<NetworkResult<UserDetailsResponse>>(NetworkResult.Loading)
    override val userDetailsState: StateFlow<NetworkResult<UserDetailsResponse>>
        get() = _userDetailsState

    override fun fetchUserDetails(id: Int) {

        _userDetailsState.value = NetworkResult.Loading

        viewModelScope.launch {
            fetchUserDetailsByIdUseCase.invoke(id).collect {
                _userDetailsState.value = it
            }
        }
    }
}