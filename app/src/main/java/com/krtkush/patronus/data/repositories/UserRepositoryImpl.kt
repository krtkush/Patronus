package com.krtkush.patronus.data.repositories

import com.krtkush.patronus.datasource.remote.rest.model.details.UserDetailsResponse
import com.krtkush.patronus.data.ApiHelper
import com.krtkush.patronus.datasource.remote.rest.model.list.UserListResponse
import com.krtkush.patronus.feature.UserRepository
import com.krtkush.patronus.utils.network.BaseApiResponse
import com.krtkush.patronus.utils.network.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val apiHelper: ApiHelper) :
    UserRepository,
    BaseApiResponse() {

    override suspend fun fetchUsersList() : Flow<NetworkResult<UserListResponse>> {

        return flow{
            emit(safeApiCall { apiHelper.getUsersList() })
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun fetchUserDetails(id: Int): Flow<NetworkResult<UserDetailsResponse>> {

        return flow{
            emit(safeApiCall { apiHelper.getUserDetails(id) })
        }.flowOn(Dispatchers.IO)
    }
}