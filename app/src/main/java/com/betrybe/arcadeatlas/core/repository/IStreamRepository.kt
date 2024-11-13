package com.betrybe.arcadeatlas.core.repository

import com.betrybe.arcadeatlas.data.DataResponse
import com.betrybe.arcadeatlas.data.dto.TwitchStreamResponse
import com.betrybe.arcadeatlas.data.dto.TwitchStreamResponseData

interface IStreamRepository {
    suspend fun getStreams(twitchGameId: String?, twitchUserLogin: String?, Language: String?): DataResponse<ArrayList<TwitchStreamResponse>>
    suspend fun getTwitchGameId(gameId: String?): DataResponse<String>
}