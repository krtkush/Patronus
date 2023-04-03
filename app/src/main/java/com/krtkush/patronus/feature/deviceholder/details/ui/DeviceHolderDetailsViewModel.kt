package com.krtkush.patronus.feature.deviceholder.details.ui

import com.krtkush.patronus.datasource.remote.rest.model.details.UserDetailsResponse
import com.krtkush.patronus.datasource.remote.rest.model.list.UserListResponse
import com.krtkush.patronus.utils.network.NetworkResult
import kotlinx.coroutines.flow.StateFlow

interface DeviceHolderDetailsViewModel {

    val userDetailsState: StateFlow<NetworkResult<UserDetailsResponse>>
    fun fetchUserDetails(id: Int)
}