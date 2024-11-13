package com.betrybe.arcadeatlas.data


sealed class DataResponse <T>(
    data: T? = null,
    exception: Exception? = null
) {
    data class Success <T>(val data: T) : DataResponse<T>(data, null)

    data class Error <T>(
        val exception: Exception
    ) : DataResponse<T>(null, exception)

}
