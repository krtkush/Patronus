package com.krtkush.patronus.datasource.remote.rest

import com.krtkush.patronus.data.models.UserListResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("users")
    suspend fun getUsersList() : Response<UserListResponseModel>

    @GET("users/{id}")
    suspend fun getUserDetails(
        @Path("id") userId: Int
    )
}