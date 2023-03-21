package com.krtkush.patronus.datasource.remote.rest

import com.krtkush.patronus.data.models.UserListResponseModel
import retrofit2.Response

interface ApiHelperInterface {

    suspend fun getUsersList() : Response<UserListResponseModel>
}