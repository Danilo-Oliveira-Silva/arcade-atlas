package com.betrybe.arcadeatlas.ui.components.gamelist

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.betrybe.arcadeatlas.ui.common.NavigationViewModel
import com.betrybe.arcadeatlas.ui.theme.ArcadeAtlasTheme

@Composable
fun GameList(navigationViewModel: NavigationViewModel) {
    val gameListViewModel = hiltViewModel<GameListViewModel>()
    //gameListViewModel.loadGames("GTA")

    Scaffold { innerPadding ->
        Column (
            verticalArrangement = Arrangement.Top,
            modifier =  Modifier.padding(bottom = innerPadding.calculateBottomPadding() - innerPadding.calculateBottomPadding())
        ) {
            OutlinedTextField(
                value = gameListViewModel.search,
                onValueChange = { gameListViewModel.updateSearch(it) },
                label = { Text("Search") },
                leadingIcon = {
                    Icon(imageVector = Icons.Outlined.Search,
                        contentDescription = "Search")
                },
                trailingIcon = {
                    if (gameListViewModel.search.isNotEmpty()) {
                        IconButton(onClick = { gameListViewModel.closeSearch() }) {
                            Icon(
                                imageVector = Icons.Outlined.Close,
                                contentDescription = "Close Search"
                            )
                        }

                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp, horizontal = 12.dp),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        gameListViewModel.search()
                    }
                )
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp)

            ) {
                item {

                }
                items(gameListViewModel.games) { game ->
                    GameCard(navigationViewModel, game)
                }
            }
        }
    }
}

