package com.betrybe.arcadeatlas.data.api

import com.betrybe.arcadeatlas.core.models.Game
import com.betrybe.arcadeatlas.data.dto.GameResponse
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("games")
    suspend fun getGame(@Body request: RequestBody): Response<List<GameResponse>>
}