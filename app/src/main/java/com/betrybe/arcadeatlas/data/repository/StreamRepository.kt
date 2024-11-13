package com.betrybe.arcadeatlas.data.repository

import com.betrybe.arcadeatlas.core.repository.IStreamRepository
import com.betrybe.arcadeatlas.data.DataResponse
import com.betrybe.arcadeatlas.data.api.ApiInterfaceTwitch
import com.betrybe.arcadeatlas.data.dto.TwitchStreamResponse
import com.betrybe.arcadeatlas.data.dto.TwitchStreamResponseData
import javax.inject.Inject

class StreamRepository @Inject constructor (private val api: ApiInterfaceTwitch) : IStreamRepository{
    override suspend fun getStreams(
        twitchGameId: String?,
        twitchUserLogin: String?,
        Language: String?
    ): DataResponse<ArrayList<TwitchStreamResponse>> {
        var response = api.getStreams(twitchGameId,twitchUserLogin, Language)
        if (response.isSuccessful) {
            var responseBody = response.body()
            return DataResponse.Success(data = responseBody!!.data!!)
        }else {
            return DataResponse.Error(exception = Exception("Error on read data"))
        }
    }

    override suspend fun getTwitchGameId(gameId: String?): DataResponse<String> {
        var response = api.getTwitchGame(gameId)
        if (response.isSuccessful) {
            var responseBody = response.body()
            try {
                return DataResponse.Success(data = responseBody!!.data!![0].id)
            } catch(e: Exception) {
                return DataResponse.Error(exception = Exception("Error on read data"))
            }
        } else {
            return DataResponse.Error(exception = Exception("Error on read data"))
        }
    }
}