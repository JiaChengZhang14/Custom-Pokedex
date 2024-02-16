package com.dam.pokedex.models.PokemonDetails

import kotlinx.serialization.Serializable

@Serializable
data class Variety(
    val is_default: Boolean,
    val pokemon: Pokemon
)