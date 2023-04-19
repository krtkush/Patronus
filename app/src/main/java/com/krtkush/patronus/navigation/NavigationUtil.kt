package com.krtkush.patronus.navigation

import androidx.navigation.NavController
import androidx.navigation.NavDirections
import com.krtkush.patronus.feature.deviceholder.details.ui.DeviceHolderDetailsFragmentDirections
import com.krtkush.patronus.feature.deviceholder.list.ui.DeviceHolderListFragmentDirections
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavigationUtil @Inject constructor() {

    private var navController: NavController? = null

    fun setController(controller: NavController) {
        navController = controller
    }

    fun clear() {
        navController = null
    }

    // Generic navigation functions.

    fun navigateBack() {
        navController?.popBackStack()
    }

    private fun navigate(direction: NavDirections) {

        if (navController == null) {
            throw IllegalStateException("NavController is null. Did you forget to call setController()?")
        } else {
            navController!!.navigate(direction)
        }
    }

    // Specific navigation functions for each fragment.

    fun navigateToDeviceHolderList() {
        navigate(DeviceHolderListFragmentDirections.actionGlobalGotoDeviceHolderListFragment())
    }

    fun navigateToDeviceHolderDetails(userId: Int) {
        navigate(DeviceHolderDetailsFragmentDirections.actionGlobalGotoDeviceHolderDetailsFragment(userId))
    }
}