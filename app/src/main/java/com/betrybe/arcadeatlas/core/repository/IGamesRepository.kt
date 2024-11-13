package com.betrybe.arcadeatlas.core.repository

import com.betrybe.arcadeatlas.core.models.Game
import com.betrybe.arcadeatlas.data.DataResponse
import retrofit2.Response

interface IGamesRepository {
    suspend fun get(gameId: String): DataResponse<Game>
    suspend fun getAll(search: String): DataResponse<ArrayList<Game>>
}