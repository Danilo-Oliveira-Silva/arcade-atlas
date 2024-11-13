package com.betrybe.arcadeatlas.data.dto

data class TwitchData(
    val data: ArrayList<TwitchDataGame>?
)

data class TwitchDataGame(
    val igdb_id: String,
    val id: String
)
