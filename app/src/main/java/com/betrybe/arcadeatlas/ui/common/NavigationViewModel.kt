package com.betrybe.arcadeatlas.ui.common

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NavigationViewModel @Inject constructor() : ViewModel(){
    lateinit var navHostController : NavHostController
        private set;

    fun onEvent(event: NavigationEvent) {
        when (event) {
            is NavigationEvent.OnSetContent -> {
                navHostController = event.navHostController
            }
            is NavigationEvent.OnNavigateToScreen -> {
                //navHostController.popBackStack()
                navHostController.navigate(event.screenEnum.name)
            }
            is NavigationEvent.OnNavigateToGameScreen -> {
                navigateToGameScreen(event.gameId)
            }
        }
    }


    private fun navigateToGameScreen(gameId : Int) {
        //navHostController.popBackStack()
        navHostController.navigate("${ScreenEnum.GameScreen.name}/$gameId")
    }
}