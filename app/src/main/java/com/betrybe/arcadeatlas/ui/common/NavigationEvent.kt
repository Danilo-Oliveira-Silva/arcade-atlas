package com.betrybe.arcadeatlas.ui.common

import androidx.navigation.NavHostController

sealed interface NavigationEvent {
    data class OnSetContent(val navHostController : NavHostController) : NavigationEvent
    data class OnNavigateToScreen(val screenEnum : ScreenEnum) : NavigationEvent
    data class OnNavigateToGameScreen(val gameId : Int): NavigationEvent
}