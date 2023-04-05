package com.krtkush.patronus.navigation

import android.os.Bundle
import androidx.navigation.NavController
import com.krtkush.patronus.R
import com.krtkush.patronus.feature.NavigationHelper
import com.krtkush.patronus.navigation.NavigationDestinations.Companion.DEVICE_HOLDER_LIST
import com.krtkush.patronus.navigation.NavigationDestinations.Companion.DEVICE_HOLDER_DETAILS
import javax.inject.Inject

class NavigationHelperImpl @Inject constructor(
        private val navController: NavController
    ) : NavigationHelper {

    override fun navigateTo(destination: String, bundle: Bundle) {

        when(destination) {
            DEVICE_HOLDER_LIST -> navController.navigate(R.id.action_global_goto_deviceHolderListFragment, bundle)
            DEVICE_HOLDER_DETAILS -> navController.navigate(R.id.action_global_goto_deviceHolderDetailsFragment, bundle)
        }
    }

    override fun navigateBack() {
        navController.popBackStack()
    }
}