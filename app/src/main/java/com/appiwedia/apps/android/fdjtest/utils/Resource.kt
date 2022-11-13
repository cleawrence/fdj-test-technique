package com.appiwedia.apps.android.fdjtest.utils

sealed class Resource<out T>(
    val data: T? = null,
    val error: String? = null,
) {
    class Success<T>(data: T) : Resource<T>(data)
    object Loading : Resource<Nothing>()
    class Error<T>(message: String?, data: T? = null) : Resource<T>(data, message)
}
