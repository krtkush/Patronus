package com.krtkush.patronus.datasource.remote.rest

import com.krtkush.patronus.data.models.user.details.UserDetailsResponse
import com.krtkush.patronus.data.models.user.list.UserListResponse
import retrofit2.Response

interface ApiHelperInterface {
    suspend fun getUsersList() : Response<UserListResponse>

    suspend fun getUserDetails(id: Int) : Response<UserDetailsResponse>
}