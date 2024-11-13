package com.betrybe.arcadeatlas.core.models

data class Platform(
    val id: Int,
    val name: String?,
    val abbreviation: String?,
    var releaseDate: Int
)
