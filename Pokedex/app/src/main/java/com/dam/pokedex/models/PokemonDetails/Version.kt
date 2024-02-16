package com.dam.pokedex.models.PokemonDetails

import kotlinx.serialization.Serializable


@Serializable
data class Version(
    val name: String,
    val url: String
)