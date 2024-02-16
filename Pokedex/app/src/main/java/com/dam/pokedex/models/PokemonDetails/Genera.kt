package com.dam.pokedex.models.PokemonDetails

import kotlinx.serialization.Serializable

@Serializable
data class Genera(
    val genus: String,
    val language: Language
)