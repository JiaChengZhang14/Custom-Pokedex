package com.dam.pokedex.models.PokemonDetails

import kotlinx.serialization.Serializable


@Serializable
data class PokedexNumber(
    val entry_number: Int,
    val pokedex: Pokedex
)