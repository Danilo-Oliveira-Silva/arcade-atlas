package com.betrybe.arcadeatlas.data.repository

import android.util.Log
import com.betrybe.arcadeatlas.core.models.Company
import com.betrybe.arcadeatlas.core.models.Game
import com.betrybe.arcadeatlas.core.models.Genre
import com.betrybe.arcadeatlas.core.models.Image
import com.betrybe.arcadeatlas.core.models.Platform
import com.betrybe.arcadeatlas.core.repository.IGamesRepository
import com.betrybe.arcadeatlas.core.repository.ITwitchRepository
import com.betrybe.arcadeatlas.data.DataResponse
import com.betrybe.arcadeatlas.data.api.ApiInterface
import com.betrybe.arcadeatlas.data.api.ApiInterfaceTwitch
import com.betrybe.arcadeatlas.data.dto.GameResponse
import com.betrybe.arcadeatlas.data.dto.TwitchData
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import retrofit2.Response
import javax.inject.Inject

class TwitchRepository @Inject constructor (private val api: ApiInterfaceTwitch) : ITwitchRepository {
    override suspend fun getTop(): DataResponse<TwitchData> {
        var response = api.getTopGames()
        if (response.isSuccessful) {
            var responseBody = response.body()
            return DataResponse.Success(data = responseBody!!)
        }else {
            return DataResponse.Error(exception = Exception("Error on read data"))
        }
    }

}