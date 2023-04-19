package com.krtkush.patronus.main.fragment

import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.navigation.fragment.findNavController
import com.krtkush.patronus.navigation.NavigationUtil
import javax.inject.Inject

class BaseFragmentLifeCycleObserver @Inject constructor(private val navigationUtil: NavigationUtil) : DefaultLifecycleObserver {

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)

        navigationUtil.setController((owner as Fragment).findNavController())
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
    }
}