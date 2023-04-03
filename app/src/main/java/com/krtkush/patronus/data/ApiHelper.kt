package com.krtkush.patronus.data

import com.krtkush.patronus.datasource.remote.rest.model.details.UserDetailsResponse
import com.krtkush.patronus.datasource.remote.rest.model.list.UserListResponse
import retrofit2.Response

interface ApiHelper {

    suspend fun getUsersList() : Response<UserListResponse>
    suspend fun getUserDetails(id: Int) : Response<UserDetailsResponse>
}