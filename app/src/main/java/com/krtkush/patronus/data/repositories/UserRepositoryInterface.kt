package com.krtkush.patronus.data.repositories

import com.krtkush.patronus.data.models.UserListResponseModel
import com.krtkush.patronus.utils.network.NetworkResult
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface UserRepositoryInterface {

    suspend fun fetchUsersList() : Flow<NetworkResult<UserListResponseModel>>
}