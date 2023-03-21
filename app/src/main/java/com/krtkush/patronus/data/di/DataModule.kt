package com.krtkush.patronus.data.di

import com.krtkush.patronus.data.repositories.UserRepositoryImpl
import com.krtkush.patronus.data.repositories.UserRepositoryInterface
import com.krtkush.patronus.datasource.remote.rest.ApiHelperInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideUserRepository(apiHelperInterface: ApiHelperInterface) : UserRepositoryInterface {
        return UserRepositoryImpl(apiHelperInterface)
    }
}