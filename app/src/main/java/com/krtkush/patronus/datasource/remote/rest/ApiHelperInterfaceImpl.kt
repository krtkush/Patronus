package com.krtkush.patronus.datasource.remote.rest

import com.krtkush.patronus.data.models.UserListResponseModel
import retrofit2.Response
import javax.inject.Inject

class ApiHelperInterfaceImpl @Inject constructor(private val apiService: ApiService) : ApiHelperInterface {

    override suspend fun getUsersList(): Response<UserListResponseModel> = apiService.getUsersList()
}