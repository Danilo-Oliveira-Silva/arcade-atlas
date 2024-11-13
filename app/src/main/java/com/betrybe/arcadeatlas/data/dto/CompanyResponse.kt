package com.betrybe.arcadeatlas.data.dto

data class CompanyResponse(
    val id: Int,
    val name: String?
)

data class GameCompaniesResponse(
    val id: Int,
    val developer: Boolean,
    val company: CompanyResponse
)
