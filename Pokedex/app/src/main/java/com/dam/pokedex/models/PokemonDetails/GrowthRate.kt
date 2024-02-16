package com.dam.pokedex.models.PokemonDetails

import kotlinx.serialization.Serializable


@Serializable
data class GrowthRate(
    val name: String,
    val url: String
)