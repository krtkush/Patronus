package com.krtkush.patronus.datasource.remote.rest

import com.krtkush.patronus.data.models.user.details.UserDetailsResponse
import com.krtkush.patronus.data.models.user.list.UserListResponse
import retrofit2.Response
import javax.inject.Inject

class ApiHelperInterfaceImpl @Inject constructor(private val apiService: ApiService) : ApiHelperInterface {

    override suspend fun getUsersList(): Response<UserListResponse> = apiService.getUsersList()

    override suspend fun getUserDetails(id: Int): Response<UserDetailsResponse> = apiService.getUserDetails(id)
}