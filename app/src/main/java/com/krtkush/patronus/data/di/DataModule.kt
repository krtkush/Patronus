package com.krtkush.patronus.data.di

import com.krtkush.patronus.data.repositories.UserRepositoryImpl
import com.krtkush.patronus.feature.UserRepository
import com.krtkush.patronus.data.ApiHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideUserRepository(apiHelper: ApiHelper) : UserRepository {
        return UserRepositoryImpl(apiHelper)
    }
}