package com.betrybe.arcadeatlas.ui.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.VideogameAsset
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.betrybe.arcadeatlas.R
import com.betrybe.arcadeatlas.ui.common.NavigationEvent
import com.betrybe.arcadeatlas.ui.common.NavigationViewModel
import com.betrybe.arcadeatlas.ui.common.ScreenEnum
import com.betrybe.arcadeatlas.ui.components.game.GameScreen
import com.betrybe.arcadeatlas.ui.components.gamelist.GameList
import com.betrybe.arcadeatlas.ui.components.home.HomeScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavigationScreen() {
    val navController = rememberNavController()
    val navigationViewModel: NavigationViewModel = hiltViewModel()

    var selectedIndex by remember {
        mutableStateOf(0)
    }
    navigationViewModel.onEvent(NavigationEvent.OnSetContent(navController))

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar (
                //containerColor = MaterialTheme.colorScheme.tertiary,
                containerColor = Color.Transparent,
                modifier = Modifier
                    .height(125.dp)
                    .paint(
                        painterResource(id = R.drawable.degrade),
                        contentScale = ContentScale.FillBounds
                    )

                    /*.background(
                    Brush.horizontalGradient(
                        colorStops = arrayOf(
                            0f to MaterialTheme.colorScheme.background,
                            0.2f to MaterialTheme.colorScheme.primary,
                            0.4f to MaterialTheme.colorScheme.background,
                            0.6f to MaterialTheme.colorScheme.secondary,
                            0.8f to MaterialTheme.colorScheme.background
                        )
                    )
                )
                */
            ) {
                NavigationBarItem(
                    selected = if (selectedIndex == 0) true else false,
                    onClick = { selectedIndex = 0
                        navigationViewModel
                            .onEvent(NavigationEvent.OnNavigateToScreen(ScreenEnum.HomeScreen)) },
                    icon = {
                        Icon(
                            imageVector = Icons.Rounded.Home,
                            contentDescription = "Home Screen",
                            modifier = Modifier.size(30.dp)
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color(0xFF8CABFF),
                        indicatorColor = Color.Transparent,
                        unselectedIconColor = Color.White
                    ),
                    label = { Text("Home", color = Color.White )}
                )
                NavigationBarItem(
                    selected = if (selectedIndex == 1) true else false,
                    onClick = { selectedIndex = 1
                        navigationViewModel
                            .onEvent(NavigationEvent.OnNavigateToScreen(ScreenEnum.GameList)) },
                    icon = {
                        Icon(
                            imageVector = Icons.Rounded.VideogameAsset,
                            contentDescription = "Game List Screen",
                            modifier = Modifier.size(30.dp)
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color(0xFF8CABFF),
                        indicatorColor = Color.Transparent,
                        unselectedIconColor = Color.White
                    ),
                    label = { Text("Games", color = Color.White )}
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navigationViewModel.navHostController,
            startDestination = ScreenEnum.HomeScreen.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = ScreenEnum.HomeScreen.name) {
                HomeScreen(navigationViewModel)
            }

            composable(route = ScreenEnum.GameList.name) {
                GameList(navigationViewModel)
            }

            composable(route = "${ScreenEnum.GameScreen.name}/{gameId}",
                        arguments = listOf(navArgument("gameId") { type = NavType.IntType })) {
                val gameId = it.arguments!!.getInt("gameId")
                GameScreen(navigationViewModel, gameId)
            }
        }
    }
}

