package com.dam.pokedex.viewModel

import com.dam.pokedex.models.Pokemon.Pokemon

class SharedViewModel {
    private var actualPokemon: Pokemon? = null
    private val myPokemons: MutableList<Pokemon> = mutableListOf()

    fun setActualPokemonValue(pokemon: Pokemon?){
        actualPokemon = pokemon!!
    }

    fun getActualPokemonValue(): Pokemon? {
        return actualPokemon
    }

    fun addToMyPokemons(pokemonInfo: Pokemon) {
        myPokemons.add(pokemonInfo)
    }

    fun getMyPokemons(): MutableList<Pokemon>{
        return myPokemons
    }
}