package com.betrybe.arcadeatlas.ui.components.home

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.betrybe.arcadeatlas.R
import com.betrybe.arcadeatlas.core.models.Company
import com.betrybe.arcadeatlas.core.models.Game
import com.betrybe.arcadeatlas.core.models.Genre
import com.betrybe.arcadeatlas.core.models.Image
import com.betrybe.arcadeatlas.core.models.Platform
import com.betrybe.arcadeatlas.ui.common.NavigationEvent
import com.betrybe.arcadeatlas.ui.common.NavigationViewModel
import com.betrybe.arcadeatlas.ui.components.game.GameScreen
import com.betrybe.arcadeatlas.ui.theme.ArcadeAtlasTheme

@Composable
fun GameHomeCard(navigationViewModel: NavigationViewModel?, game: Game) {
    Card(shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .padding(top = 5.dp)
            .height(310.dp)
            .background(MaterialTheme.colorScheme.background, MaterialTheme.shapes.extraSmall)
            .clickable {
                navigationViewModel!!.onEvent(NavigationEvent.OnNavigateToGameScreen(game.id))
            })
    {
        Column (
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.padding(vertical = 10.dp)
        ) {
            AsyncImage(
                model = game.coverImage?.url,
                placeholder = painterResource(id = R.drawable.game_cover),
                error = painterResource(id = R.drawable.game_cover),
                contentDescription = "Game cover",
                contentScale = ContentScale.Crop,
                modifier = Modifier.height(250.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = game.name!!,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.width(200.dp)
            )
        }
    }
}

@Preview(showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun GreetingPreview() {

    var game = Game (
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
    )
    ArcadeAtlasTheme {
        GameHomeCard(null, game)
    }
}