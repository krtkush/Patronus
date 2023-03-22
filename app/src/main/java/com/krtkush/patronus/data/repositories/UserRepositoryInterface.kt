package com.krtkush.patronus.data.repositories

import com.krtkush.patronus.data.models.user.details.UserDetailsResponse
import com.krtkush.patronus.data.models.user.list.UserListResponse
import com.krtkush.patronus.utils.network.NetworkResult
import kotlinx.coroutines.flow.Flow

interface UserRepositoryInterface {
    suspend fun fetchUsersList() : Flow<NetworkResult<UserListResponse>>

    suspend fun fetchUserDetails(id: Int) : Flow<NetworkResult<UserDetailsResponse>>
}