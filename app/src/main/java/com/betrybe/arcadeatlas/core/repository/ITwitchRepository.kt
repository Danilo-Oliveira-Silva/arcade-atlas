package com.betrybe.arcadeatlas.core.repository

import com.betrybe.arcadeatlas.core.models.Game
import com.betrybe.arcadeatlas.data.DataResponse
import com.betrybe.arcadeatlas.data.dto.TwitchData
import retrofit2.Response

interface ITwitchRepository {
    suspend fun getTop(): DataResponse<TwitchData>
}