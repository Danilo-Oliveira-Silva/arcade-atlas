package com.betrybe.arcadeatlas.data.repository

import android.util.Log
import com.betrybe.arcadeatlas.core.models.Company
import com.betrybe.arcadeatlas.core.models.Game
import com.betrybe.arcadeatlas.core.models.Genre
import com.betrybe.arcadeatlas.core.models.Image
import com.betrybe.arcadeatlas.core.models.Platform
import com.betrybe.arcadeatlas.core.repository.IGamesRepository
import com.betrybe.arcadeatlas.data.DataResponse
import com.betrybe.arcadeatlas.data.api.ApiInterface
import com.betrybe.arcadeatlas.data.dto.GameResponse
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import retrofit2.Response
import javax.inject.Inject

class GamesRepository @Inject constructor (private val api: ApiInterface) : IGamesRepository {
    override suspend fun get(gameId: String): DataResponse<Game> {
        val request : String = "fields \n" +
                "name,\n" +
                "category,\n" +
                "cover.image_id,\n" +
                "cover.url,\n" +
                "screenshots.image_id,\n" +
                "first_release_date,\n" +
                "genres.name,\n" +
                "platforms.name,\n" +
                "platforms.abbreviation,\n" +
                "release_dates.human,\n" +
                "release_dates.date,\n" +
                "release_dates.platform.abbreviation,\n" +
                "storyline, summary,\n" +
                "themes.name,\n" +
                "involved_companies.company.name,\n" +
                "involved_companies.developer,\n" +
                "version_parent,\n" +
                "rating"+
                "; where id = "+gameId+";"
        var requestBody: RequestBody = RequestBody.create("text/plain".toMediaTypeOrNull(), request)
        var response = api.getGame(requestBody)

        if (response.isSuccessful) {
            response.body().let {
                return DataResponse.Success(data = gameResponseToGame(it!![0]))
            }
        } else {
            return DataResponse.Error(exception = Exception("Error on read data"))
        }
    }

    override suspend fun getAll(search: String): DataResponse<ArrayList<Game>> {
        val request : String = "fields \n" +
                "name, \n" +
                "genres.name,\n" +
                "cover.image_id,\n" +
                "cover.url,\n" +
                "rating;\n" +
                "search \""+search+"\"; \n" +
                "where version_parent = null & category != 3; \n" +
                "limit 20;\n"

        var requestBody: RequestBody = RequestBody.create("text/plain".toMediaTypeOrNull(), request)
        var response = api.getGame(requestBody)

        var listGames = arrayListOf<Game>()
        if (response.isSuccessful) {
            var responseBody = response.body()
            Log.e("Danilo", responseBody.toString())

            responseBody!!.forEach { gameResponse ->
                listGames.add(gameResponseListToGame(gameResponse))
            }

            return DataResponse.Success(data = listGames)
        } else {
            return DataResponse.Error(exception = Exception("Error on read data"))
        }
    }

    private fun gameResponseListToGame(gameResponse: GameResponse) : Game {
        val game = Game(
            id = gameResponse.id,
            name = gameResponse.name,
            rating = gameResponse.rating,
            coverImage = gameResponse.cover?.let {
                Image(
                    id = it.id,
                    imageId = gameResponse.cover.image_id,
                    url = "https://images.igdb.com/igdb/image/upload/t_cover_big/"+gameResponse.cover.image_id+".jpg"
                )
            },
            genres = arrayListOf<Genre>(),
            developerCompany = null,
            firstReleaseDate = null,
            images = null,
            platforms = null,
            storyline = null,
            summary = null
        )

        gameResponse.genres?.forEach {
            game.genres?.add(Genre(
                id = it.id,
                name = it.name
            ))
        }

        return game

    }
    private fun gameResponseToGame(gameResponse: GameResponse) : Game {
        var game = Game(
            id = gameResponse.id,
            name = gameResponse.name,
            rating = gameResponse.rating,
            summary = gameResponse.summary,
            storyline = gameResponse.storyline,
            coverImage = Image(
                id = gameResponse.cover!!.id,
                imageId = gameResponse.cover!!.image_id,
                url = "https://images.igdb.com/igdb/image/upload/t_cover_big/"+gameResponse.cover.image_id+".jpg"
            ),
            firstReleaseDate = gameResponse.first_release_date,
            developerCompany = null,
            genres = arrayListOf<Genre>(),
            platforms = arrayListOf<Platform>(),
            images = arrayListOf<Image>()
        )

        gameResponse.involved_companies?.forEach {
            if (it.developer) {
                game.developerCompany = Company (
                    id = it.company.id,
                    name = it.company.name
                )
            }
        }

        gameResponse.genres?.forEach {
            game.genres?.add(Genre(
                id = it.id,
                name = it.name
            ))
        }

        gameResponse.screenshots?.forEach {
            game.images?.add(Image(
                id = it.id,
                imageId = it.image_id,
                url = "https://images.igdb.com/igdb/image/upload/t_720p/"+it.image_id+".jpg"
            ))
        }

        gameResponse.platforms?.forEach{
            game.platforms?.add(Platform(
                    id = it.id,
                    name = it.name,
                    abbreviation = it.abbreviation,
                    releaseDate = 0
                )
            )
        }

        gameResponse.release_dates?.forEach { rd ->
            game.platforms?.forEach {
                if (it.id == rd.platform.id) it.releaseDate = rd.date
            }
        }

        if (game.rating == null) game.rating = 0.0
        if (game.images == null || game.images!!.isEmpty()) {
            game.images?.add(Image(
                0, "", ""
            ))
        }
        return game
    }
}