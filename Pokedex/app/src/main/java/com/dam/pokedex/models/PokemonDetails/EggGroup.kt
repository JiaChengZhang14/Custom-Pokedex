package com.dam.pokedex.models.PokemonDetails

import kotlinx.serialization.Serializable

@Serializable
data class EggGroup(
    val name: String,
    val url: String
)