package com.krtkush.patronus.domain.di

import com.krtkush.patronus.data.repositories.UserRepositoryInterface
import com.krtkush.patronus.domain.usecases.FetchUserDetailsByIdUseCase
import com.krtkush.patronus.domain.usecases.FetchUsersUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DomainModule {

    @Provides
    @Singleton
    fun provideFetchUsersUseCase(userRepositoryInterface: UserRepositoryInterface) : FetchUsersUseCase {
        return FetchUsersUseCase(userRepositoryInterface)
    }

    @Provides
    @Singleton
    fun provideFetchUserDetailsByIdUseCase(userRepositoryInterface: UserRepositoryInterface) : FetchUserDetailsByIdUseCase {
        return FetchUserDetailsByIdUseCase(userRepositoryInterface)
    }
}