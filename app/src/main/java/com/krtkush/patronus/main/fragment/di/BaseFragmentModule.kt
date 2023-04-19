package com.krtkush.patronus.main.fragment.di

import com.krtkush.patronus.main.fragment.BaseFragmentLifeCycleObserver
import com.krtkush.patronus.navigation.NavigationUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
class BaseFragmentModule {

    @Provides
    fun provideFragmentLifecycleObserver(navigationUtil: NavigationUtil): BaseFragmentLifeCycleObserver {
        return BaseFragmentLifeCycleObserver(navigationUtil)
    }
}