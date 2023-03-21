package com.krtkush.patronus.utils.network

sealed class NetworkResult<out T> {
    object Loading: NetworkResult<Nothing>()
    data class Error(val message: String): NetworkResult<Nothing>()
    data class Success<T>(val data: T): NetworkResult<T>()
}