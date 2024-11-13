package com.betrybe.arcadeatlas.data.api

import com.betrybe.arcadeatlas.core.models.Game
import com.betrybe.arcadeatlas.data.dto.GameResponse
import com.betrybe.arcadeatlas.data.dto.TwitchData
import com.betrybe.arcadeatlas.data.dto.TwitchStreamResponseData
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiInterfaceTwitch {
    @GET("helix/games/top")
    suspend fun getTopGames(): Response<TwitchData>

    @GET("helix/streams")
    suspend fun getStreams(@Query("game_id") gameId: String?, @Query("user_login") userLogin: String?, @Query("language") language: String?): Response<TwitchStreamResponseData>

    @GET("helix/games")
    suspend fun getTwitchGame(@Query("igdb_id") gameId: String?): Response<TwitchData>
}


