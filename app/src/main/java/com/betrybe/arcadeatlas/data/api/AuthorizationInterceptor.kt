package com.betrybe.arcadeatlas.data.api

import okhttp3.Interceptor
import okhttp3.Response

class AuthorizationInterceptor(private val bearerToken : String, private val clientId: String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val newRequest = originalRequest.newBuilder()
            .header("Authorization", "Bearer $bearerToken")
            .header("Client-ID", clientId)
            .method(originalRequest.method, originalRequest.body)
            .build()

        return chain.proceed(newRequest)
    }
}