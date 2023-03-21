package com.krtkush.patronus.datasource.remote.rest

import com.krtkush.patronus.data.models.users.list.UserListResponse
import retrofit2.Response

interface ApiHelperInterface {

    suspend fun getUsersList() : Response<UserListResponse>
}