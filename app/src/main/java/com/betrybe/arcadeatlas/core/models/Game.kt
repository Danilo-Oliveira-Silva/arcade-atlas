package com.betrybe.arcadeatlas.core.models

data class Game(
    val id: Int,
    val name: String?,
    var rating: Double?,
    val storyline: String?,
    val summary: String?,
    val coverImage: Image?,
    val firstReleaseDate: Int?,
    var developerCompany: Company?,
    val genres: ArrayList<Genre>?,
    val platforms: ArrayList<Platform>?,
    val images: ArrayList<Image>?

)
