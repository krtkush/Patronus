package com.krtkush.patronus.feature.deviceholder.list.ui

import com.krtkush.patronus.datasource.remote.rest.model.list.UserListResponse
import com.krtkush.patronus.utils.network.NetworkResult
import kotlinx.coroutines.flow.StateFlow

interface DeviceHolderListViewModel {

    val userListState: StateFlow<NetworkResult<UserListResponse>>
    fun fetchUsers()
}