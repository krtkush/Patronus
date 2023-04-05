package com.krtkush.patronus.utils.ui

import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class GlideRequestFailureListenerWrapper<T>(
    private val onError: ((e: Exception?) -> Unit)? = null
) : RequestListener<T> {

    override fun onLoadFailed(
        e: GlideException?,
        model: Any?,
        target: Target<T>?,
        isFirstResource: Boolean
    ): Boolean {
        onError?.invoke(e)
        return false
    }

    override fun onResourceReady(
        resource: T,
        model: Any?,
        target: Target<T>?,
        dataSource: DataSource?,
        isFirstResource: Boolean
    ): Boolean {
        return false
    }
}