package com.krtkush.patronus.feature

import com.krtkush.patronus.datasource.remote.rest.model.details.UserDetailsResponse
import com.krtkush.patronus.datasource.remote.rest.model.list.UserListResponse
import com.krtkush.patronus.utils.network.NetworkResult
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    suspend fun fetchUsersList() : Flow<NetworkResult<UserListResponse>>
    suspend fun fetchUserDetails(id: Int) : Flow<NetworkResult<UserDetailsResponse>>
}