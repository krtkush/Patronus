package com.krtkush.patronus.domain.usecases

import com.krtkush.patronus.data.models.UserListResponseModel
import com.krtkush.patronus.data.repositories.UserRepositoryInterface
import com.krtkush.patronus.utils.network.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchUsersUseCase @Inject constructor(
    private val userRepo : UserRepositoryInterface) {

    suspend operator fun invoke(): Flow<NetworkResult<UserListResponseModel>> = userRepo.fetchUsersList()
}