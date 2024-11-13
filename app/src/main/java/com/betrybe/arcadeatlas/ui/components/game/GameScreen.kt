package com.betrybe.arcadeatlas.ui.components.game

import RatingBar
import android.annotation.SuppressLint
import android.content.res.Configuration
import android.icu.util.TimeZone
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.betrybe.arcadeatlas.R
import com.betrybe.arcadeatlas.core.models.Company
import com.betrybe.arcadeatlas.core.models.Game
import com.betrybe.arcadeatlas.core.models.Genre
import com.betrybe.arcadeatlas.core.models.Image
import com.betrybe.arcadeatlas.core.models.Platform
import com.betrybe.arcadeatlas.ui.common.NavigationViewModel
import com.betrybe.arcadeatlas.ui.components.stream.StreamCard
import com.betrybe.arcadeatlas.ui.theme.ArcadeAtlasTheme
import java.time.Instant
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalLayoutApi::class)
@SuppressLint("DefaultLocale")
@Composable
fun GameScreen(navigationViewModel: NavigationViewModel?, gameId: Int) {

    val gameViewModel = hiltViewModel<GameViewModel>()
    gameViewModel.loadGame(gameId)
    gameViewModel.loadStreams(gameId)

    val minimumLineLength = 3
    var expandedState by remember { mutableStateOf(false) }
    var showReadMoreButtonState by remember { mutableStateOf(false) }
    val maxLines = if (expandedState) 200 else minimumLineLength


    /*var game = Game (
        id = 1020,
        name = "Grand Theft Auto V",
        rating = 89.53511411401522,
        storyline = "\"Los Santos: a sprawling sun-soaked metropolis full of self-help gurus, starlets and fading celebrities, once the envy of the Western world, now struggling to stay afloat in an era of economic uncertainty and cheap reality TV.\\n\\nAmidst the turmoil, three very different criminals plot their own chances of survival and success: Franklin, a street hustler looking for real opportunities and serious money; Michael, a professional ex-con whose retirement is a lot less rosy than he hoped it would be; and Trevor, a violent maniac driven by the chance of a cheap high and the next big score. Running out of options, the crew risks everything in a series of daring",
        summary = "Grand Theft Auto V is a vast open world game set in Los Santos, a sprawling sun-soaked metropolis struggling to stay afloat in an era of economic uncertainty and cheap reality TV. The game blends storytelling and gameplay in new ways as players repeatedly jump in and out of the lives of the game’s three lead characters, playing all sides of the game’s interwoven story",
        coverImage = Image(id = 120937, imageId = "co2lbd", url = "https://images.igdb.com/igdb/image/upload/t_cover_big/co2lbd.jpg"),
        developerCompany = Company(id = 365, name = "Rockstar North"),
        firstReleaseDate = 1379376000,
        genres = arrayListOf<Genre>(
            Genre(id = 5, name = "Shooter"),
            Genre(id = 10, name = "Racing"),
            Genre(id = 31, name = "Adventure")
        ),
        images = arrayListOf<Image>(
            Image(id = 5670, imageId = "o7q3ikzmkjxbftrd64ok", url = "https://images.igdb.com/igdb/image/upload/t_720p/o7q3ikzmkjxbftrd64ok.jpg"),
            Image(id = 5671, imageId = "vfdeo6kgu0o4cyzd0sng", url = "https://images.igdb.com/igdb/image/upload/t_720p/vfdeo6kgu0o4cyzd0sng.jpg"),
            Image(id = 5672, imageId = "eepecmqsq6uqxiaukar1", url = "https://images.igdb.com/igdb/image/upload/t_720p/eepecmqsq6uqxiaukar1.jpg"),
            Image(id = 5673, imageId = "hjnzngnrtwr82jzmmkef", url = "https://images.igdb.com/igdb/image/upload/t_720p/hjnzngnrtwr82jzmmkef.jpg"),
            Image(id = 5674, imageId = "glvmulyejlde31q8b70z", url = "https://images.igdb.com/igdb/image/upload/t_720p/glvmulyejlde31q8b70z.jpg"),
            Image(id = 6913, imageId = "n3t2agwuxlqggp3kryf9", url = "https://images.igdb.com/igdb/image/upload/t_720p/n3t2agwuxlqggp3kryf9.jpg")
        ),
        platforms = arrayListOf<Platform>(
            Platform(id = 49, name = "Xbox One", abbreviation = "XONE", releaseDate = 1416268800),
            Platform(id = 48, name = "PlayStation 4", abbreviation = "PS4", releaseDate = 1416268800),
            Platform(id = 12, name = "Xbox 360", abbreviation = "X360", releaseDate = 1379376000),
            Platform(id = 9, name = "PlayStation 3", abbreviation = "PS3", releaseDate = 1379376000),
            Platform(id = 6, name = "PC (Microsoft Windows)", abbreviation = "PC", releaseDate = 1428883200)
        )
    )*/

    Scaffold (
        containerColor = MaterialTheme.colorScheme.background,
        modifier = Modifier.padding(0.dp)
        ) { innerPadding ->

        if (gameViewModel.isLoaded) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(top = innerPadding.calculateTopPadding())
                .background(
                    Brush.verticalGradient(
                        colorStops = arrayOf(
                            0.5f to MaterialTheme.colorScheme.background,
                            0.9f to MaterialTheme.colorScheme.secondary
                        )
                    )
                ),
            verticalArrangement = Arrangement.Top
        ) {

            Box(
                modifier = Modifier
                    .height(250.dp)
                    .fillMaxWidth()
                    .background(Color.Blue)
            ) {
                AsyncImage(
                    model = gameViewModel.game.images?.get(0)?.url,
                    placeholder = painterResource(id = R.drawable.game_banner),
                    error = painterResource(id = R.drawable.game_banner),
                    contentDescription = "Game banner",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxWidth()
                )

                Box(
                    modifier = Modifier
                        .height(150.dp)
                        .fillMaxWidth()
                        .align(Alignment.TopStart)
                        .offset(y = 110.dp)
                        .background(
                            Brush.verticalGradient(
                                colorStops = arrayOf(
                                    0f to Color.Transparent,
                                    0.7f to MaterialTheme.colorScheme.background
                                )
                            )
                        )
                )

                AsyncImage(
                    model = gameViewModel.game.coverImage?.url,
                    placeholder = painterResource(id = R.drawable.game_cover),
                    error = painterResource(id = R.drawable.game_cover),
                    contentDescription = "Game cover",
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .offset(y = 115.dp, x = 10.dp)
                )

                Text(
                    text = gameViewModel.game.name!!,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .offset(x = 120.dp, y = (-60).dp)
                        .fillMaxWidth(1f)
                        //.width(LocalConfiguration.current.screenWidthDp - 130)
                )

                RatingBar(
                    rating = (5 * gameViewModel.game.rating!! / 100),
                    starsColor = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .offset(x = 120.dp, y = (-30).dp)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            FlowRow(
                horizontalArrangement = Arrangement.Center,
                verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                gameViewModel.game.genres!!.forEach {
                    AssistChip(
                        onClick = { /*TODO*/ },
                        border = null,
                        label = {
                            Text(
                                text = it.name,
                                color = MaterialTheme.colorScheme.onSecondary,
                                style = MaterialTheme.typography.bodyMedium,
                            )
                        },
                        colors = AssistChipDefaults.assistChipColors(
                            containerColor = MaterialTheme.colorScheme.secondary
                        ),
                        modifier = Modifier
                            .height(30.dp)
                            .padding(horizontal = 5.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = gameViewModel.game.summary!!,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(horizontal = 20.dp),
                overflow = TextOverflow.Ellipsis,
                maxLines = maxLines,
                onTextLayout = { textLayoutResult: TextLayoutResult ->
                    if (textLayoutResult.lineCount > minimumLineLength - 1) {           //Adding this check to avoid ArrayIndexOutOfBounds Exception
                        if (textLayoutResult.isLineEllipsized(minimumLineLength - 1)) showReadMoreButtonState =
                            true
                    }
                }
            )

            if (showReadMoreButtonState) {
                Text(
                    text = if (expandedState) "Read Less" else "Read More",
                    color = MaterialTheme.colorScheme.secondary,

                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .clickable {
                            expandedState = !expandedState
                        },

                    style = MaterialTheme.typography.bodySmall

                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Streams",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(20.dp))

            LazyRow {
                items(gameViewModel.streams) { stream ->
                    StreamCard(navigationViewModel = navigationViewModel, stream = stream)
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Images",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(20.dp))

            FlowRow(
                horizontalArrangement = Arrangement.Center,
                verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                gameViewModel.game.images!!.forEach {
                    AsyncImage(
                        model = it?.url,
                        placeholder = painterResource(id = R.drawable.game_banner),
                        error = painterResource(id = R.drawable.game_banner),
                        contentDescription = "Game banner",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .padding(8.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Platforms",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Column(
                //horizontalArrangement = Arrangement.Center,
                verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                gameViewModel.game.platforms!!.forEach {

                    val sdf = java.text.SimpleDateFormat("MM/dd/yyyy")
                    val date = java.util.Date(it.releaseDate.toLong() * 1000)
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 20.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,

                        ) {
                        Text(
                            text = it.name!!,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            text = sdf.format(date).toString(),
                            style = MaterialTheme.typography.bodySmall
                        )

                    }
                    Divider(
                        color = MaterialTheme.colorScheme.tertiary,
                        thickness = 0.5.dp,
                        modifier = Modifier.padding(horizontal = 20.dp)
                    )
                }
            }

        }
    }
    }



}


@Preview(showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun GreetingPreview() {
    ArcadeAtlasTheme {
      GameScreen(null, 1020)
    }
}