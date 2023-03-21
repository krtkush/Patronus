package com.krtkush.patronus.data.repositories

import com.krtkush.patronus.datasource.remote.rest.ApiHelperInterface
import com.krtkush.patronus.data.models.UserListResponseModel
import com.krtkush.patronus.utils.network.BaseApiResponse
import com.krtkush.patronus.utils.network.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val apiHelper: ApiHelperInterface) :
    UserRepositoryInterface,
    BaseApiResponse() {

    override suspend fun fetchUsersList() : Flow<NetworkResult<UserListResponseModel>> {

        return flow{
            emit(safeApiCall { apiHelper.getUsersList() })
        }.flowOn(Dispatchers.IO)
    }
}