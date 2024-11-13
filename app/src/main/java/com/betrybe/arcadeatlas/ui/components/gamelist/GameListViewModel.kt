package com.betrybe.arcadeatlas.ui.components.gamelist

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.betrybe.arcadeatlas.core.models.Game
import com.betrybe.arcadeatlas.data.DataResponse
import com.betrybe.arcadeatlas.data.repository.GamesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameListViewModel @Inject constructor(
    private val gamesRepository: GamesRepository
) : ViewModel(){

    var games by mutableStateOf(listOf<Game>())
        private set

    var search by mutableStateOf("")
        private set

    fun updateSearch(input: String) {
        search = input
    }

    fun closeSearch() {
        updateSearch("")
    }


    fun search() {
        if (this.search.isNotEmpty()) loadGames(this.search)
    }


    fun loadGames(search: String) {
        viewModelScope.launch {
            val gameListResponse = gamesRepository.getAll(search)
            if (gameListResponse is DataResponse.Success)
            {
                games = gameListResponse.data
            }
            if (gameListResponse is DataResponse.Error) {
                Log.e("Error", gameListResponse.exception.toString())
            }
        }
    }
}