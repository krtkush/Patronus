package com.krtkush.patronus.datasource.remote.rest

import com.krtkush.patronus.datasource.remote.rest.model.details.UserDetailsResponse
import com.krtkush.patronus.datasource.remote.rest.model.list.UserListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("users")
    suspend fun getUsersList() : Response<UserListResponse>

    @GET("users/{id}")
    suspend fun getUserDetails(
        @Path("id") userId: Int
    ) : Response<UserDetailsResponse>
}