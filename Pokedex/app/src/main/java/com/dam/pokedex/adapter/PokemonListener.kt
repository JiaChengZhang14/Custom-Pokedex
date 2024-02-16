package com.dam.pokedex.adapter

import com.dam.pokedex.models.Pokemon.Pokemon

interface PokemonListener {
    fun onClick(pokemon: Pokemon)
}