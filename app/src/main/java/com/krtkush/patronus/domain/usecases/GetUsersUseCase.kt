package com.krtkush.patronus.domain.usecases

import com.krtkush.patronus.data.models.UserListResponseModel
import com.krtkush.patronus.data.repositories.UserRepositoryInterface
import com.krtkush.patronus.utils.network.NetworkResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val userRepo : UserRepositoryInterface) {

    suspend operator fun invoke(): Flow<NetworkResult<UserListResponseModel>> = userRepo.fetchUsersList()
}