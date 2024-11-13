package com.betrybe.arcadeatlas.data.dto

data class GameResponse(
    val id: Int,
    val name: String?,
    val rating: Double?,
    val storyline: String?,
    val summary: String?,
    val cover: ImageResponse?,
    val genres: ArrayList<GenreResponse>?,
    val first_release_date: Int?,
    val involved_companies: ArrayList<GameCompaniesResponse>?,
    val platforms: ArrayList<PlatformResponse>?,
    val release_dates: ArrayList<PlatformRealeaseResponse>?,
    val screenshots: ArrayList<ImageResponse>?
)



