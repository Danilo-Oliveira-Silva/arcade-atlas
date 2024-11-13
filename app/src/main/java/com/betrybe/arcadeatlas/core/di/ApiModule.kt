package com.betrybe.arcadeatlas.core.di

import com.betrybe.arcadeatlas.data.api.ApiInterface
import com.betrybe.arcadeatlas.data.api.ApiInterfaceTwitch
import com.betrybe.arcadeatlas.data.api.AuthorizationInterceptor
import com.betrybe.arcadeatlas.data.api.AuthorizationInterceptorTwitch
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    @Singleton
    fun provideApi() : ApiInterface {
        val baseUrl = "https://api.igdb.com/v4/"
        val bearerToken = "token-igdb"
        val clientId = "client-id-twitch"
        val authorizationInterceptor = AuthorizationInterceptor(bearerToken, clientId)
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(authorizationInterceptor)
            .build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ApiInterface::class.java)
    }

    @Provides
    @Singleton
    fun provideApiTwitch() : ApiInterfaceTwitch {
        val baseUrl = "https://api.twitch.tv/"
        val bearerToken = "token-twitch"
        val clientId = "client-id-twitch"
        val authorizationInterceptorTwitch = AuthorizationInterceptorTwitch(bearerToken, clientId)

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(authorizationInterceptorTwitch)
            .build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(ApiInterfaceTwitch::class.java)
    }
}