package com.betrybe.arcadeatlas.ui.components.game

import android.provider.ContactsContract.Data
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.betrybe.arcadeatlas.core.models.Company
import com.betrybe.arcadeatlas.core.models.Game
import com.betrybe.arcadeatlas.core.models.Genre
import com.betrybe.arcadeatlas.core.models.Image
import com.betrybe.arcadeatlas.core.models.Platform
import com.betrybe.arcadeatlas.data.DataResponse
import com.betrybe.arcadeatlas.data.dto.TwitchStreamResponse
import com.betrybe.arcadeatlas.data.repository.GamesRepository
import com.betrybe.arcadeatlas.data.repository.StreamRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val gamesRepository: GamesRepository,
    private val streamRepository: StreamRepository
) : ViewModel(){

    var isLoaded by mutableStateOf(false)
    var game by mutableStateOf(
        Game (
            id = 0,
            name = "",
            rating = 0.0,
            storyline = "",
            summary = "",
            coverImage = Image(id = 0, imageId = "", url = ""),
            developerCompany = Company(id = 0, name = ""),
            firstReleaseDate = 0,
            genres = arrayListOf<Genre>(),
            images = arrayListOf<Image>(),
            platforms = arrayListOf<Platform>()
        )
    )
        private set

    var streams by mutableStateOf(ArrayList<TwitchStreamResponse>())
        private set

    fun loadStreams(gameId: Int) {
        viewModelScope.launch {
            val twitchGameId = streamRepository.getTwitchGameId(gameId.toString())
            if (twitchGameId is DataResponse.Success)
            {
                val streamsCall = streamRepository.getStreams(twitchGameId.data, null, "pt")
                if (streamsCall is DataResponse.Success) {
                    streams = streamsCall.data
                }
            }

        }

    }

    fun loadGame(gameId: Int) {

        viewModelScope.launch {
            val gameFound = gamesRepository.get(gameId.toString())
            if (gameFound is DataResponse.Success) {
                game = gameFound.data
                isLoaded = true
            }
            if (gameFound is DataResponse.Error) {
                Log.e("Error", gameFound.exception.toString())
            }
        }
    }
}