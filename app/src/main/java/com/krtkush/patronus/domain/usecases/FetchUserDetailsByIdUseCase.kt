package com.krtkush.patronus.domain.usecases

import com.krtkush.patronus.datasource.remote.rest.model.details.UserDetailsResponse
import com.krtkush.patronus.feature.UserRepository
import com.krtkush.patronus.utils.network.NetworkResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchUserDetailsByIdUseCase @Inject constructor(
    private val userRepo : UserRepository
) {
    suspend operator fun invoke(id: Int): Flow<NetworkResult<UserDetailsResponse>> = userRepo.fetchUserDetails(id)
}