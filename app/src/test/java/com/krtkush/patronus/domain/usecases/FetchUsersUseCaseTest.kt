package com.krtkush.patronus.domain.usecases

import com.krtkush.patronus.data.models.user.list.Customer
import com.krtkush.patronus.data.models.user.list.UserListResponse
import com.krtkush.patronus.data.repositories.UserRepositoryInterface
import com.krtkush.patronus.utils.network.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.stubbing.OngoingStubbing
import retrofit2.Response

@RunWith(org.mockito.junit.MockitoJUnitRunner::class)
class FetchUsersUseCaseTest {

    @Test
    suspend fun test() = runBlocking {

        val userRepositoryMock = mock(UserRepositoryInterface::class.java)
        `when`(userRepositoryMock.fetchUsersList()).thenAnswer {

            flow<NetworkResult<UserListResponse>> { emit(

                NetworkResult.Success(
                    UserListResponse(
                        listOf(
                            Customer(
                                1,
                                "name",
                                "email",
                                "phone",
                                "website",
                                "company",
                                listOf("")
                            )
                        )
                    )
                )
            ) }
        }

        val fetchUsersUseCase = FetchUsersUseCase(userRepositoryMock)

        val result = fetchUsersUseCase.invoke()
        assertNotNull(result)
    }

}