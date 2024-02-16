package com.dam.pokedex.models.PokemonDetails

import kotlinx.serialization.Serializable

@Serializable
data class Color(
    val name: String,
    val url: String
)