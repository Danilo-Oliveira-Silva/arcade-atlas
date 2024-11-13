package com.betrybe.arcadeatlas.ui.components.gamelist

import RatingBar
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.room.util.TableInfo
import coil.compose.AsyncImage
import com.betrybe.arcadeatlas.R
import com.betrybe.arcadeatlas.core.models.Game
import com.betrybe.arcadeatlas.ui.common.NavigationEvent
import com.betrybe.arcadeatlas.ui.common.NavigationViewModel
import com.betrybe.arcadeatlas.ui.components.game.GameScreen
import com.betrybe.arcadeatlas.ui.theme.ArcadeAtlasTheme


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun GameCard(navigationViewModel: NavigationViewModel, game: Game) {

    var ratingValue = 0.0
    if (game.rating != null) ratingValue = game.rating!!

    Card(shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .background(MaterialTheme.colorScheme.background, MaterialTheme.shapes.extraSmall)
            .clickable {
                navigationViewModel.onEvent(NavigationEvent.OnNavigateToGameScreen(game.id))
            })
    {
        Row (
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            AsyncImage(
                model = game.coverImage?.url,
                placeholder = painterResource(id = R.drawable.game_cover),
                error = painterResource(id = R.drawable.game_cover),
                contentDescription = "Game cover",
            )

            Column (
                verticalArrangement = Arrangement.Top,
                modifier = Modifier.padding(start = 10.dp)
            ) {
                Text(
                    text = game.name!!,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                )

                RatingBar(
                    rating = (5 * ratingValue / 100),
                    starsColor = MaterialTheme.colorScheme.secondary,
                )

                FlowRow (
                    modifier = Modifier
                        .padding(top = 6.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
                ){
                    game.genres!!.forEach {
                        AssistChip(
                            onClick = { /*TODO*/ },
                            border = null,
                            label = {
                                Text(
                                    text = it.name,
                                    color = MaterialTheme.colorScheme.onSecondary,
                                    style = MaterialTheme.typography.bodySmall,
                                )
                            },
                            colors = AssistChipDefaults.assistChipColors(
                                containerColor = MaterialTheme.colorScheme.secondary
                            ),
                            modifier = Modifier
                                .height(20.dp)
                                .padding(horizontal = 2.dp)
                        )
                    }
                }
            }
        }
    }
}

