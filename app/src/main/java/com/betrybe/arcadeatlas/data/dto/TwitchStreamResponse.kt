package com.betrybe.arcadeatlas.data.dto


data class TwitchStreamResponseData (
    val data: ArrayList<TwitchStreamResponse>?
)
data class TwitchStreamResponse(
    val user_id: String?,
    val user_login: String?,
    val game_id: String?,
    val game_name: String?,
    val title: String?,
    val viewer_count: Int?,
    val thumbnail_url: String?
)
