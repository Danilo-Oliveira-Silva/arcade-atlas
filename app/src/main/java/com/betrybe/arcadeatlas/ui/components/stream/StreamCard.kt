package com.betrybe.arcadeatlas.ui.components.stream

import android.content.Intent
import android.content.res.Configuration
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Person2
import androidx.compose.material.icons.rounded.Person3
import androidx.compose.material.icons.rounded.VideogameAsset
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.betrybe.arcadeatlas.R
import com.betrybe.arcadeatlas.core.models.Game
import com.betrybe.arcadeatlas.data.dto.TwitchStreamResponse
import com.betrybe.arcadeatlas.ui.common.NavigationEvent
import com.betrybe.arcadeatlas.ui.common.NavigationViewModel
import com.betrybe.arcadeatlas.ui.components.home.GameHomeCard
import com.betrybe.arcadeatlas.ui.theme.ArcadeAtlasTheme
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.haze


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun StreamCard(navigationViewModel: NavigationViewModel?, stream: TwitchStreamResponse) {
    val ctx = LocalContext.current
    val hazeState = remember {
        HazeState()
    }
    Card(shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),
        modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .background(MaterialTheme.colorScheme.background, MaterialTheme.shapes.extraSmall)
            .clickable {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://twitch.tv/" + stream.user_login))
                ctx.startActivity(intent)
                //navigationViewModel!!.onEvent(NavigationEvent.OnNavigateToGameScreen(game.id))
            })
    {
        Box {
            AsyncImage(
                model = imageLink(stream.thumbnail_url!!),
                placeholder = painterResource(id = R.drawable.game_banner),
                error = painterResource(id = R.drawable.game_cover),
                contentDescription = "Game cover",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(240.dp)
                    .height(135.dp)
            )



            Box(
                modifier = Modifier
                    .height(55.dp)
                    .width(240.dp)
                    .align(Alignment.BottomStart)
                    .background(Brush.verticalGradient(
                        colorStops = arrayOf(
                            0f to Color.Transparent,
                            0.7f to MaterialTheme.colorScheme.secondary
                        )
                    ))
                    .blur(radius = 500.dp)
                    /*.haze(
                        hazeState,
                        backgroundColor = Color.Red,
                        //tint = Color.Black.copy(alpha = 1f),
                        //blurRadius = 30.dp,
                    )*/
            )

            Text(text = stream.title!!,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .offset(x = 20.dp, y = -28.dp)
                    .align(Alignment.BottomStart)
                    .width(200.dp)
            )

            Text(text = "@" + stream.user_login!!,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .offset(x = 20.dp, y = -10.dp)
                    .align(Alignment.BottomStart)
                    .width(200.dp)
            )

            Icon(
                imageVector = Icons.Rounded.Person2,
                contentDescription = "Views",
                modifier = Modifier.size(20.dp).align(Alignment.BottomEnd).offset(x = -10.dp, y = -10.dp),
                tint = MaterialTheme.colorScheme.onBackground
            )

            Text(text = stream.viewer_count.toString(),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.offset(x = -30.dp, y = -10.dp)
                    .align(Alignment.BottomEnd)
            )
        }
    }
}

private fun imageLink(url: String) : String {
    return url.replace("{width}", "240").replace("{height}", "135")
}

@Preview(showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun GreetingPreview() {
    var stream = TwitchStreamResponse (
            user_id = "181077473",
            user_login = "gaules",
            game_id = "1608807548",
            game_name = "Wizard With a Gun",
            title = "DIA DE LOUCURA VEMMMM - Siga @Gaules nas redes sociais!",
            viewer_count = 8067,
            thumbnail_url = "https://static-cdn.jtvnw.net/previews-ttv/live_user_gaules-{width}x{height}.jpg"
    )

    ArcadeAtlasTheme {
        StreamCard(null, stream)
    }
}