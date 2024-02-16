package com.dam.pokedex.models.PokemonDetails

import kotlinx.serialization.Serializable

@Serializable
data class Habitat(
    val name: String,
    val url: String
)