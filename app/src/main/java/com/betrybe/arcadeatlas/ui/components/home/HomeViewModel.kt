package com.betrybe.arcadeatlas.ui.components.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.betrybe.arcadeatlas.core.models.Game
import com.betrybe.arcadeatlas.data.DataResponse
import com.betrybe.arcadeatlas.data.dto.TwitchStreamResponse
import com.betrybe.arcadeatlas.data.repository.GamesRepository
import com.betrybe.arcadeatlas.data.repository.StreamRepository
import com.betrybe.arcadeatlas.data.repository.TwitchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@SuppressLint("MutableCollectionMutableState")
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val twitchRepository: TwitchRepository,
    private val gamesRepository: GamesRepository,
    private val streamRepository: StreamRepository
) : ViewModel(){

    var isLoaded by mutableStateOf(false)

    var gamesId by mutableStateOf(ArrayList<String>())
        private set

    var games by mutableStateOf(ArrayList<Game>())
        private set

    var streams by mutableStateOf(ArrayList<TwitchStreamResponse>())
        private set

    suspend fun loadStreams() {
        val streamsCall = streamRepository.getStreams(null, null, "pt")
        if (streamsCall is DataResponse.Success) {
            streams = streamsCall.data
        }
    }
    fun loadTopGames() {
        if (!isLoaded) {
            viewModelScope.launch {
                games.clear()
                gamesId.clear()
                val listTopGames = twitchRepository.getTop()
                if (listTopGames is DataResponse.Success) {
                    listTopGames.data.data!!.forEach { game ->
                        if (game.igdb_id.isNotEmpty()) {

                            gamesId.add(game.igdb_id)
                        }
                    }
                    gamesId.forEach { gameId ->
                        var gameFound = gamesRepository.get(gameId)
                        if (gameFound is DataResponse.Success) {
                            games.add(gameFound.data)
                        }
                        if (gameFound is DataResponse.Error) {
                            Log.e("Error", gameFound.exception.toString())
                        }
                    }

                    Log.e("DANILO", games.toString())
                    isLoaded = true
                }
                if (listTopGames is DataResponse.Error) {
                    Log.e("Error", listTopGames.exception.toString())
                    isLoaded = true
                }

                loadStreams()
            }
        }
    }
}