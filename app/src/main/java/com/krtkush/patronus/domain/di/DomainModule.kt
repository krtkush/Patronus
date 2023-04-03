package com.krtkush.patronus.domain.di

import com.krtkush.patronus.feature.UserRepository
import com.krtkush.patronus.domain.usecases.FetchUserDetailsByIdUseCase
import com.krtkush.patronus.domain.usecases.FetchUsersUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    @Singleton
    fun provideFetchUsersUseCase(userRepository: UserRepository) : FetchUsersUseCaseImpl {
        return FetchUsersUseCaseImpl(userRepository)
    }

    @Provides
    @Singleton
    fun provideFetchUserDetailsByIdUseCase(userRepository: UserRepository) : FetchUserDetailsByIdUseCase {
        return FetchUserDetailsByIdUseCase(userRepository)
    }
}