package com.betrybe.arcadeatlas.data.dto

data class PlatformResponse(
    val id: Int,
    val name: String?,
    val abbreviation: String?
)

data class PlatformRealeaseResponse (
    val id: Int,
    val date: Int,
    val platform: PlatformResponse
)