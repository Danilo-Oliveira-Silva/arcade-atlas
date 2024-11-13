package com.betrybe.arcadeatlas.ui.components.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.betrybe.arcadeatlas.R
import com.betrybe.arcadeatlas.ui.common.NavigationEvent
import com.betrybe.arcadeatlas.ui.common.NavigationViewModel
import com.betrybe.arcadeatlas.ui.components.gamelist.GameCard
import com.betrybe.arcadeatlas.ui.components.stream.StreamCard

@Composable
fun HomeScreen(navigationViewModel: NavigationViewModel) {
    val homeViewModel = hiltViewModel<HomeViewModel>();
    LaunchedEffect(Unit) {
        homeViewModel.loadTopGames()
    }
    Scaffold (
    ) { innerPadding ->
        Column(modifier = Modifier.padding(top = innerPadding.calculateTopPadding(), bottom = 0.dp)
            .verticalScroll(rememberScrollState())) {
            Image(
                painter = painterResource(id = R.drawable.logo_noback),
                contentDescription = "Logotipo",
                modifier = Modifier.height(60.dp).align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Top Twitch Games",
                style = MaterialTheme.typography.titleLarge,
                color = Color(0xFF5DA6FF),
                modifier = Modifier
                    .padding(horizontal = 20.dp)
            )

            if (homeViewModel.isLoaded) {
                LazyRow {
                    items(homeViewModel.games) { game ->
                        GameHomeCard(navigationViewModel, game)
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Top Streams",
                style = MaterialTheme.typography.titleLarge,
                color = Color(0xFF5DA6FF),
                modifier = Modifier
                    .padding(horizontal = 20.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            if (homeViewModel.isLoaded) {
                LazyRow {
                    items(homeViewModel.streams) { stream ->
                        StreamCard(navigationViewModel = navigationViewModel, stream = stream)
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))


        }
    }
}

