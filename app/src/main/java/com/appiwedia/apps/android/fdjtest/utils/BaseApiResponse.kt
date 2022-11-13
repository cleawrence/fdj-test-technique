package com.appiwedia.apps.android.fdjtest.utils

import retrofit2.Response


abstract class BaseApiResponse {
    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): Resource<T> {
        return try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    return Resource.Success(body)
                } ?: kotlin.run {
                    return Resource.Error("Une erreur inattendue est survenue")
                }
            }
            error("${response.code()} ${response.message()}")
        } catch (e: Exception) {
            error(e.message ?: e.toString())
        }
    }

    private fun <T> error(errorMessage: String): Resource<T> =
        Resource.Error("Api call failed $errorMessage")
}