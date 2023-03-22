package com.krtkush.patronus.domain.usecases

import com.krtkush.patronus.data.models.user.details.UserDetailsResponse
import com.krtkush.patronus.data.repositories.UserRepositoryInterface
import com.krtkush.patronus.utils.network.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchUserDetailsByIdUseCase @Inject constructor(
    private val userRepo : UserRepositoryInterface
) {
    suspend operator fun invoke(id: Int): Flow<NetworkResult<UserDetailsResponse>> = userRepo.fetchUserDetails(id)
}