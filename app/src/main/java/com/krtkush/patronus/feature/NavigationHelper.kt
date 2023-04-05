package com.krtkush.patronus.feature

import android.os.Bundle

interface NavigationHelper {

    fun navigateTo(destination: String, bundle: Bundle)
    fun navigateBack()
}