package com.krtkush.patronus.domain.usecases

import com.krtkush.patronus.datasource.remote.rest.model.list.Customer
import com.krtkush.patronus.datasource.remote.rest.model.list.UserListResponse
import com.krtkush.patronus.feature.UserRepository
import com.krtkush.patronus.utils.network.NetworkResult
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

@RunWith(org.mockito.junit.MockitoJUnitRunner::class)
class FetchUsersUseCaseTest {

    @Test
    suspend fun test() = runBlocking {

        val userRepositoryMock = mock(UserRepository::class.java)
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