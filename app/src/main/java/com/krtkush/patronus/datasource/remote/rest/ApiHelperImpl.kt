package com.krtkush.patronus.datasource.remote.rest

import com.krtkush.patronus.data.ApiHelper
import com.krtkush.patronus.datasource.remote.rest.model.details.UserDetailsResponse
import com.krtkush.patronus.datasource.remote.rest.model.list.UserListResponse
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {

    override suspend fun getUsersList(): Response<UserListResponse> = apiService.getUsersList()

    override suspend fun getUserDetails(id: Int): Response<UserDetailsResponse> = apiService.getUserDetails(id)
}