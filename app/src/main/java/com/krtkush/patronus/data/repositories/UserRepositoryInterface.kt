package com.krtkush.patronus.data.repositories

import com.krtkush.patronus.data.models.users.list.UserListResponse
import com.krtkush.patronus.utils.network.NetworkResult
import kotlinx.coroutines.flow.Flow

interface UserRepositoryInterface {

    suspend fun fetchUsersList() : Flow<NetworkResult<UserListResponse>>
}