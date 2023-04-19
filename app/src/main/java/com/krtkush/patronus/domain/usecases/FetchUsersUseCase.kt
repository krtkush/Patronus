package com.krtkush.patronus.domain.usecases

import com.krtkush.patronus.datasource.remote.rest.model.list.UserListResponse
import com.krtkush.patronus.feature.UserRepository
import com.krtkush.patronus.utils.network.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchUsersUseCase @Inject constructor(
    private val userRepo : UserRepository
) {

    suspend operator fun invoke(): Flow<NetworkResult<UserListResponse>> = userRepo.fetchUsersList()
}